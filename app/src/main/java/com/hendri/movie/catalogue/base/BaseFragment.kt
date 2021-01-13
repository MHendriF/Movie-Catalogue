package com.hendri.movie.catalogue.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

abstract class BaseFragment<FragmentBinding : ViewDataBinding> : Fragment() {

    protected abstract val layoutFragment: Int
    protected lateinit var binding: FragmentBinding

    override fun onCreateView(li: LayoutInflater, con: ViewGroup?, bundle: Bundle?): View? {
        binding = DataBindingUtil.inflate(li, layoutFragment, con, false)
        return binding.root
    }
}