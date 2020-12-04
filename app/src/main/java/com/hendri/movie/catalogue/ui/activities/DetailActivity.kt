package com.hendri.movie.catalogue.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseActivity
import com.hendri.movie.catalogue.databinding.ActivityDetailBinding
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel.Companion.DATA_DESTINATION
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel.Companion.DATA_ID
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val DATA_EXTRA = "DATA_EXTRA"
        const val DATA_EXTRA_ID = "DATA_EXTRA_ID"
    }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var navController: NavController

    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }

    override val layoutActivity: Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntegerArrayListExtra(DATA_EXTRA)?.apply {
            viewModel.setDataExtra(get(DATA_DESTINATION), get(DATA_ID))
        }

        navController = nav_host_detail_fragment.findNavController()
        savedInstanceState?.let {
            navController.restoreState(it)
        } ?: run {
            navController.navInflater.inflate(R.navigation.nav_graph_detail).run {
                startDestination = viewModel.getDataExtra(DATA_DESTINATION)
                navController
                    .setGraph(this, bundleOf(DATA_EXTRA_ID to viewModel.getDataExtra(DATA_ID)))
            }
        }
    }

    override fun onRefresh() {
        viewModel.dataTv = null
        viewModel.dataMovie = null
        val dataDes = viewModel.getDataExtra(DATA_DESTINATION)
        val dataId = viewModel.getDataExtra(DATA_ID)
        viewModel.setDataExtra(dataDes, dataId)
        navController.navigate(dataDes, bundleOf(DATA_EXTRA_ID to dataId))
    }

}