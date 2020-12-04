package com.hendri.movie.catalogue

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController

class SingleNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_navigation)
    }

    fun startDestination(@NavigationRes navigation: Int, @IdRes destination: Int) {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_single_fragment)!!
        val navController = navHost.findNavController()
        navController.navInflater.inflate(navigation).run {
            startDestination = destination
            navController.graph = this
        }
    }
}