package com.hendri.movie.catalogue.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.model.DetailTvShow
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.databinding.FragmentDetailTvShowBinding
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel

class DetailTvShowFragment : BaseFragment<FragmentDetailTvShowBinding>() {

    private val viewModel by activityViewModels<DetailViewModel>()

    override val layoutFragment: Int = R.layout.fragment_detail_tv_show

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
        viewModel.tvShow.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<DetailTvShow>) {
        when (resource) {
            is Resource.Loading -> binding.isLoading = true
            is Resource.Empty -> binding.isLoading = false
            is Resource.Success -> {
                binding.isLoading = false
                resource.data.let { data ->
                    binding.model = data
                    binding.tvReadMore.setOnClickListener {
                        if (binding.tvReadMore.text.toString() == "Read More") {
                            binding.tvOverview.maxLines = Int.MAX_VALUE
                            binding.tvOverview.ellipsize = null
                            binding.tvReadMore.setText(R.string.read_less)
                        } else {
                            binding.tvOverview.maxLines = 4
                            binding.tvOverview.ellipsize = TextUtils.TruncateAt.END
                            binding.tvReadMore.setText(R.string.read_more)
                        }
                    }
                }
            }
            is Resource.Error -> {
                binding.isLoading = false
                activity?.toast(resource.errorMessage)
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}