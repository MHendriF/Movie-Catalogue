package com.hendri.movie.catalogue.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.hendri.movie.catalogue.MyApp
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.base.adapter.ItemListener
import com.hendri.movie.catalogue.data.Resource
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.databinding.FragmentMovieBinding
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.adapters.MovieAdapter
import com.hendri.movie.catalogue.ui.viewmodels.MainViewModel
import com.hendri.movie.catalogue.viewmodel.ViewModelFactory
import javax.inject.Inject

class FavoriteMovieFragment : BaseFragment<FragmentMovieBinding>(), ItemListener<Movie> {

    companion object {
        private const val ARG_SECTION_NUMBER = "FAVORITE_MOVIE_FRAGMENT"

        fun newInstance(index: Int) = FavoriteMovieFragment().apply {
            arguments = Bundle().apply { putInt(ARG_SECTION_NUMBER, index) }
        }
    }

    @Inject
    internal lateinit var factory: ViewModelFactory

    private val viewModel by navGraphViewModels<MainViewModel>(R.id.nav_graph_main) { factory }

    private lateinit var adapter: MovieAdapter

    override val layoutFragment: Int = R.layout.fragment_movie

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MovieAdapter().apply {
            onItemListener = this@FavoriteMovieFragment
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            binding.rvMovie.adapter = this
        }
        viewModel.movieFavorite.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<PagedList<Movie>>) {
        when (resource) {
            is Resource.Loading -> binding.isLoading = true
            is Resource.Success -> {
                binding.isLoading = false
                resource.data.let { data -> adapter.submitList(data) }
            }
            is Resource.Empty -> {
                binding.isLoading = false
            }
            is Resource.Error -> {
                findNavController().getViewModelStoreOwner(R.id.nav_graph_main).viewModelStore.clear()
                binding.isLoading = true
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