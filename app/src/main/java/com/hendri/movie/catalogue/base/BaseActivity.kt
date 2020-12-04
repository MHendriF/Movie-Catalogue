package com.hendri.movie.catalogue.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<ActivityBinding : ViewDataBinding> : DaggerAppCompatActivity() {

    protected abstract val layoutActivity: Int
    protected lateinit var binding: ActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutActivity)
    }
}