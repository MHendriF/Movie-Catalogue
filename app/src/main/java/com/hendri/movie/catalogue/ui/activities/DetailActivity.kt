package com.hendri.movie.catalogue.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.ActivityDetailBinding
import com.hendri.movie.catalogue.ui.viewmodels.ContentViewModel
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var dataEntity: DataEntity

    companion object {
        const val EXTRA_DATA = "data_entity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        doInitialization()
    }

    private fun doInitialization() {
        viewModel = ViewModelProvider(this).get(ContentViewModel::class.java)
        detailBinding.ivBack.setOnClickListener { onBackPressed() }
        getDataDetails()
    }

    private fun getDataDetails() {
        detailBinding.isLoading = true
        dataEntity = intent.getParcelableExtra(EXTRA_DATA)!!
        Timber.d("Trace :: data(${dataEntity})")

        detailBinding.title = dataEntity.title
        detailBinding.releaseDate = dataEntity.releaseDate
        detailBinding.genre = dataEntity.genre
        detailBinding.description = dataEntity.description
        detailBinding.score = dataEntity.score
        detailBinding.imgPoster = dataEntity.imgPoster
        detailBinding.imgBackground = dataEntity.imgBackground
        detailBinding.tvReadMore.setOnClickListener {
            if (detailBinding.tvReadMore.text.toString() == "Read More") {
                detailBinding.tvDescription.maxLines = Int.MAX_VALUE
                detailBinding.tvDescription.ellipsize = null
                detailBinding.tvReadMore.setText(R.string.read_less)
            } else {
                detailBinding.tvDescription.maxLines = 4
                detailBinding.tvDescription.ellipsize = TextUtils.TruncateAt.END
                detailBinding.tvReadMore.setText(R.string.read_more)
            }
        }

        detailBinding.isLoading = false

    }
}