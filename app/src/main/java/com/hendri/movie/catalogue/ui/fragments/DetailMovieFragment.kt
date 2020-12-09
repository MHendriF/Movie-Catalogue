package com.hendri.movie.catalogue.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.model.DetailMovie
import com.hendri.movie.catalogue.databinding.FragmentDetailMovieBinding
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding>() {

    private val viewModel by activityViewModels<DetailViewModel>()

    override val layoutFragment: Int = R.layout.fragment_detail_movie

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
        viewModel.movie.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<DetailMovie>) {
        with(binding) {
            when (resource) {
                is Resource.Loading -> isLoading = true
                is Resource.Empty -> isLoading = false
                is Resource.Success -> {
                    isLoading = false
                    resource.data.let { data ->
                        visibleContent()
                        model = data
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
                        ivFavorite.setOnClickListener {
                            activity?.toast(data.title.toString())
                        }
                    }
                }
                is Resource.Error -> {
                    isLoading = false
                    activity?.toast(resource.message)
                }
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun visibleContent() {
        with(binding) {
            ivBackground.visibility = View.VISIBLE
            ivPoster.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            tvOverview.visibility = View.VISIBLE
            tvGenre.visibility = View.VISIBLE
            tvReleaseDate.visibility = View.VISIBLE
            tvScore.visibility = View.VISIBLE
            tvReadMore.visibility = View.VISIBLE
            ivFavorite.visibility = View.VISIBLE
        }
    }

}