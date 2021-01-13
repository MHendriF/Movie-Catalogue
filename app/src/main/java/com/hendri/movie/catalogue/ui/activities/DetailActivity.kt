package com.hendri.movie.catalogue.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hendri.movie.catalogue.MyApp
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseActivity
import com.hendri.movie.catalogue.databinding.ActivityDetailBinding
import com.hendri.movie.catalogue.ui.viewmodels.DetailViewModel
import com.hendri.movie.catalogue.viewmodel.ViewModelFactory
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    companion object {
        const val DATA_DESTINATION = 0
        const val DATA_ID = 1
        const val DATA_EXTRA = "DATA_EXTRA"
        const val DATA_EXTRA_ID = "DATA_EXTRA_ID"
    }

    @Inject
    internal lateinit var factory: ViewModelFactory

    private lateinit var navController: NavController

    private val viewModel by viewModels<DetailViewModel> { factory }

    override val layoutActivity: Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        intent.getIntegerArrayListExtra(DATA_EXTRA)?.apply {
            viewModel.init(get(DATA_DESTINATION), get(DATA_ID))
        }

        navController = Navigation.findNavController(this, R.id.nav_host_detail_fragment)
        savedInstanceState?.let {
            navController.restoreState(it)
        } ?: run {
            navController.navInflater.inflate(R.navigation.nav_graph_detail).run {
                startDestination = viewModel.getExtra(DATA_DESTINATION)
                navController
                    .setGraph(this, bundleOf(DATA_EXTRA_ID to viewModel.getExtra(DATA_ID)))
            }
        }
    }
}