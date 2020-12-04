package com.hendri.movie.catalogue.ui.activities

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.base.BaseActivity
import com.hendri.movie.catalogue.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutActivity: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        val navController = Navigation.findNavController(this, R.id.nav_host_main_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration.Builder(R.id.fragment_movie, R.id.fragment_tv).build()
        )
    }
}