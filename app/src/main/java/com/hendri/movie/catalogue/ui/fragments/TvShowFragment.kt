package com.hendri.movie.catalogue.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.api.ApiHelperImp
import com.hendri.movie.catalogue.data.api.RetrofitBuilder
import com.hendri.movie.catalogue.data.local.DatabaseBuilder
import com.hendri.movie.catalogue.data.local.DatabaseHelperImp
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.databinding.FragmentTvShowBinding
import com.hendri.movie.catalogue.ui.adapters.TvShowAdapter
import com.hendri.movie.catalogue.ui.base.ViewModelFactory
import com.hendri.movie.catalogue.ui.listeners.TvShowListener
import com.hendri.movie.catalogue.ui.viewmodels.TvShowViewModel
import com.hendri.movie.catalogue.utils.Status
import timber.log.Timber
import java.util.ArrayList

class TvShowFragment : Fragment(), TvShowListener {

    private lateinit var fragmentBinding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter
    private var tvShows: List<TvShow> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupObserver()
        doInitialization()
    }

    private fun doInitialization() {
        tvShowAdapter = TvShowAdapter(this)
        tvShowAdapter.setData(tvShows)
        fragmentBinding.rvTvShow.setHasFixedSize(true)
        fragmentBinding.rvTvShow.adapter = tvShowAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImp(RetrofitBuilder.apiService),
                DatabaseHelperImp(DatabaseBuilder.getInstance(requireContext().applicationContext))
            )
        ).get(TvShowViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getTvShowFromApi().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        fragmentBinding.isLoading = false
                        tvShows = it1
                        tvShowAdapter.setData(tvShows)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> {
                    fragmentBinding.isLoading = true
                }
                Status.ERROR -> {
                    fragmentBinding.isLoading = false
                    it.message?.let { it1 -> requireContext().toast(it1) }
                }
            }
        })
    }

    override fun onItemClicked(tvShow: TvShow) {
        Timber.d("Trace :: data(${tvShow.name})")
//        val intent = Intent(requireContext(), DetailActivity::class.java)
//        intent.putExtra(DetailActivity.EXTRA_DATA, dataEntity)
//        intent.putExtra(DetailActivity.EXTRA_TYPE, "TvShow")
//        requireActivity().startActivity(intent)
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}