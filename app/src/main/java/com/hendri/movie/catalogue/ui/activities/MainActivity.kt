package com.hendri.movie.catalogue.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.ui.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }
}