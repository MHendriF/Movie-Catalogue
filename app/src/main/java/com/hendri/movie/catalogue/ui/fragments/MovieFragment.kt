package com.hendri.movie.catalogue.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.PagedList
import com.hendri.movie.catalogue.MyApp
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.base.adapter.ItemListener
import com.hendri.movie.catalogue.vo.Resource
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

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MovieAdapter().apply {
            onItemListener = this@MovieFragment
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.adapter = this
        }
        viewModel.movie.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<PagedList<Movie>>) {
        with(binding) {
            when (resource) {
                is Resource.Loading -> isLoading = true
                is Resource.Empty -> {
                    isLoading = false
                    activity?.toast(getString(R.string.empty_message))
                }
                is Resource.Success -> {
                    isLoading = false
                    resource.data.let { data -> adapter.submitList(data) }
                }
                is Resource.Error -> {
                    isLoading = false
                    Timber.e("handleStat:  %s", resource.message)
                    findNavController().getViewModelStoreOwner(R.id.nav_graph_main).viewModelStore.clear()
                    activity?.toast(resource.message)
                }
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(model: Movie) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DATA_EXTRA, arrayListOf(R.id.detail_movie, model.id))
        }
        requireActivity().startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.by_name -> {
                item.isChecked = true
                viewModel.sorting(MainViewModel.Type.NAME);true
            }
            R.id.by_release -> {
                item.isChecked = true
                viewModel.sorting(MainViewModel.Type.RELEASE_DATA);true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}