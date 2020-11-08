package com.hendri.movie.catalogue.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.databinding.FragmentTvShowBinding
import com.hendri.movie.catalogue.ui.activities.DetailActivity
import com.hendri.movie.catalogue.ui.adapters.ContentAdapter
import com.hendri.movie.catalogue.ui.listeners.ItemListener
import com.hendri.movie.catalogue.ui.viewmodels.ContentViewModel
import timber.log.Timber

class TvShowFragment : Fragment(), ItemListener {

    private lateinit var fragmentBinding: FragmentTvShowBinding
    private lateinit var viewModel: ContentViewModel
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var tvShows : List<DataEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doInitialization()
    }

    private fun doInitialization() {
        viewModel = ViewModelProvider(this).get(ContentViewModel::class.java)
        tvShows = viewModel.getTvShows()
        contentAdapter = ContentAdapter(this)
        contentAdapter.setData(tvShows)
        fragmentBinding.rvTvShow.setHasFixedSize(true)
        fragmentBinding.rvTvShow.adapter = contentAdapter
    }

    override fun onItemClicked(dataEntity: DataEntity) {
        Timber.d("Trace :: data(${dataEntity.title})")
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, dataEntity)
        intent.putExtra(DetailActivity.EXTRA_TYPE, "TvShow")
        requireActivity().startActivity(intent)
    }
}