package com.hendri.movie.catalogue.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.PagedList
import com.hendri.movie.catalogue.MyApp
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseFragment
import com.hendri.movie.catalogue.base.adapter.ItemListener
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.databinding.FragmentTvShowBinding
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.adapters.TvShowAdapter
import com.hendri.movie.catalogue.ui.viewmodels.MainViewModel
import com.hendri.movie.catalogue.viewmodel.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class TvShowFragment : BaseFragment<FragmentTvShowBinding>(), ItemListener<TvShow> {

    @Inject
    internal lateinit var factory: ViewModelFactory

    private lateinit var adapter: TvShowAdapter
    private val viewModel by navGraphViewModels<MainViewModel>(R.id.nav_graph_main) { factory }

    override val layoutFragment: Int = R.layout.fragment_tv_show

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApp).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TvShowAdapter().apply {
            onItemListener = this@TvShowFragment
            binding.rvTvShow.setHasFixedSize(true)
            binding.rvTvShow.adapter = this
        }
        viewModel.tvShow.observe(viewLifecycleOwner, { handleStat(it) })
    }

    private fun handleStat(resource: Resource<PagedList<TvShow>>) {
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

    override fun onItemClick(model: TvShow) {
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.DATA_EXTRA, arrayListOf(R.id.detail_tv_show, model.id))
        }
        requireActivity().startActivity(intent)
    }
}