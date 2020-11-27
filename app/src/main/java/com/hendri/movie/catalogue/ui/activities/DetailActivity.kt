package com.hendri.movie.catalogue.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.data.api.ApiHelperImp
import com.hendri.movie.catalogue.data.api.RetrofitBuilder
import com.hendri.movie.catalogue.data.local.DatabaseBuilder
import com.hendri.movie.catalogue.data.local.DatabaseHelperImp
import com.hendri.movie.catalogue.databinding.ActivityDetailBinding
import com.hendri.movie.catalogue.ui.base.ViewModelFactory
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel
import com.hendri.movie.catalogue.ui.viewmodels.MovieViewModel
import com.hendri.movie.catalogue.utils.BindingAdapters
import com.hendri.movie.catalogue.utils.Constants
import com.hendri.movie.catalogue.utils.Constants.TYPE_MOVIE
import com.hendri.movie.catalogue.utils.Status
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setupViewModel()
        doInitialization()
    }

    private fun doInitialization() {
        detailBinding.ivBack.setOnClickListener { onBackPressed() }
        getDataDetails()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(
            ApiHelperImp(RetrofitBuilder.apiService),
            DatabaseHelperImp(DatabaseBuilder.getInstance(this.applicationContext))
        )).get(DetailViewModel::class.java)
    }


    private fun getDataDetails() {
        detailBinding.isLoading = true
        val id: Int = intent.getIntExtra(EXTRA_ID, -1)
        val type: String? = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE)) {
            setupObserverMovie(id)
        } else {
            setupObserverTvShow(id)
        }
    }

    private fun setupObserverMovie(id: Int) {
        viewModel.getMovieFromApi(id).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        detailBinding.isLoading = false

                        with(detailBinding) {
                            tvTitle.text = data.title
                            tvOverview.text = data.overview
                            tvLanguage.text = data.originalLanguage
                            tvScore.text = data.voteAverage.toString()
                            tvReleaseDate.text = data.releaseDate
                            BindingAdapters.setPosterURL(ivPoster, data.posterPath)
                            BindingAdapters.setBackdropURL(ivBackground, data.backdropPath)
                            visibleView()

                            tvReadMore.setOnClickListener {
                                if (tvReadMore.text.toString() == "Read More") {
                                    tvOverview.maxLines = Int.MAX_VALUE
                                    tvOverview.ellipsize = null
                                    tvReadMore.setText(R.string.read_less)
                                } else {
                                    tvOverview.maxLines = 4
                                    tvOverview.ellipsize = TextUtils.TruncateAt.END
                                    tvReadMore.setText(R.string.read_more)
                                }
                            }
                            isLoading = false
                        }
                    }
                }
                Status.LOADING -> {
                    detailBinding.isLoading = true
                }
                Status.ERROR -> {
                    detailBinding.isLoading = false
                    it.message?.let { it1 -> this.toast(it1) }
                }
            }
        })
    }

    private fun setupObserverTvShow(id: Int) {
        viewModel.getTvShowFromApi(id).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        detailBinding.isLoading = false
                        with(detailBinding) {
                            tvTitle.text = data.name
                            tvOverview.text = data.overview
                            tvLanguage.text = data.originalLanguage
                            tvScore.text = data.voteAverage.toString()
                            tvReleaseDate.text = data.firstAirDate
                            BindingAdapters.setPosterURL(ivPoster, data.posterPath)
                            BindingAdapters.setBackdropURL(ivBackground, data.backdropPath)
                            visibleView()

                            tvReadMore.setOnClickListener {
                                if (tvReadMore.text.toString() == "Read More") {
                                    tvOverview.maxLines = Int.MAX_VALUE
                                    tvOverview.ellipsize = null
                                    tvReadMore.setText(R.string.read_less)
                                } else {
                                    tvOverview.maxLines = 4
                                    tvOverview.ellipsize = TextUtils.TruncateAt.END
                                    tvReadMore.setText(R.string.read_more)
                                }
                            }
                            isLoading = false
                        }
                    }
                }
                Status.LOADING -> {
                    detailBinding.isLoading = true
                }
                Status.ERROR -> {
                    detailBinding.isLoading = false
                    it.message?.let { it1 -> this.toast(it1) }
                }
            }
        })
    }
    
    private fun visibleView() {
        with(detailBinding) {
            tvTitle.visibility = View.VISIBLE
            tvOverview.visibility = View.VISIBLE
            tvLanguage.visibility = View.VISIBLE
            tvScore.visibility = View.VISIBLE
            tvReleaseDate.visibility = View.VISIBLE
            ivPoster.visibility = View.VISIBLE
            ivBackground.visibility = View.VISIBLE
            tvReadMore.visibility = View.VISIBLE
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}