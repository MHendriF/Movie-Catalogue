package com.hendri.movie.catalogue.ui.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.data.source.Resource
import com.hendri.movie.catalogue.data.source.remote.response.TvDetailResponse
import com.hendri.movie.catalogue.databinding.FragmentDetailTvShowBinding
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel

class DetailTvShowFragment : BaseFragment<FragmentDetailTvShowBinding>() {

    private val viewModel by activityViewModels<DetailViewModel>()

    override val layoutFragment: Int = R.layout.fragment_detail_tv_show

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.dataTv?.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<TvDetailResponse>) {

        when (resource) {
            is Resource.Loading -> binding.isLoading = true
            is Resource.Empty -> binding.isLoading = false
            is Resource.Success -> {
                binding.isLoading = false
                resource.data.let { data ->
                    binding.model = data
                }
            }
            is Resource.Error -> {
                binding.isLoading = false
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}