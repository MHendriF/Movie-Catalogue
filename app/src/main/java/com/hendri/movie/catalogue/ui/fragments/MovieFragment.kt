package com.hendri.movie.catalogue.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.PagedList
import com.hendri.movie.catalogue.MyApp
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.base.adapter.ItemListener
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.databinding.FragmentMovieBinding
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.ui.viewmodels.MainViewModel
import com.hendri.movie.catalogue.viewmodel.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : BaseFragment<FragmentMovieBinding>(), ItemListener<Movie> {

    @Inject
    internal lateinit var factory: ViewModelFactory

    private val viewModel by navGraphViewModels<MainViewModel>(R.id.nav_graph_main) { factory }

    private lateinit var adapter: MovieAdapter

    override val layoutFragment: Int = R.layout.fragment_movie

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity().application as MyApp).appComponent.inject(this)

        adapter = MovieAdapter().apply {
            setHasStableIds(true)
            onItemListener = this@MovieFragment
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.adapter = this
        }

        viewModel.movie.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<PagedList<Movie>>) {
        when (resource) {
            is Resource.Loading -> binding.isLoading = true
            is Resource.Empty -> binding.isLoading = false
            is Resource.Success -> {
                binding.isLoading = false
                resource.data.let { data -> adapter.submitList(data) }
            }
            is Resource.Error -> {
                Timber.e("handleStat: error")
                findNavController().getViewModelStoreOwner(R.id.nav_graph_main).viewModelStore.clear()
                binding.isLoading = false
                Toast.makeText(requireContext(), resource.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClick(model: Movie) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DATA_EXTRA, arrayListOf(R.id.detail_movie, model.id))
        }
        requireActivity().startActivity(intent)
    }

}