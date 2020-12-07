package com.hendri.movie.catalogue.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.base.adapter.ItemListener
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.databinding.FragmentTvShowBinding
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.adapters.TvShowAdapter
import com.hendri.movie.catalogue.ui.viewmodels.MainViewModel
import javax.inject.Inject

class TvShowFragment : BaseFragment<FragmentTvShowBinding>(), ItemListener<TvShow> {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: TvShowAdapter
    private val viewModel by navGraphViewModels<MainViewModel>(R.id.nav_graph_main) { viewModelFactory }

    override val layoutFragment: Int = R.layout.fragment_tv_show

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TvShowAdapter().apply {
            setHasStableIds(true)
            onItemListener = this@TvShowFragment
            binding.rvTvShow.setHasFixedSize(true)
            binding.rvTvShow.adapter = this
        }

        viewModel.getTvShows.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<TvShowResponse>) {
        when (resource) {
            is Resource.Loading -> binding.isLoading = true
            is Resource.Empty -> binding.isLoading = false
            is Resource.Success -> {
                binding.isLoading = false
                resource.data.let { data -> adapter.data = data.results.toMutableList() }
            }
            is Resource.Error -> {
                findNavController().getViewModelStoreOwner(R.id.nav_graph_main).viewModelStore.clear()
                binding.isLoading = false
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(entity: TvShow) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DATA_EXTRA, arrayListOf(R.id.detail_tv_show, entity.id))
        }
        requireActivity().startActivity(intent)
    }
}