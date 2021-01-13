package com.hendri.movie.catalogue

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class SingleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
            id = R.id.container
            setContentView(this)
        }
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit { add(R.id.container, fragment, "TEST") }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit { add(R.id.container, fragment) }
    }
}