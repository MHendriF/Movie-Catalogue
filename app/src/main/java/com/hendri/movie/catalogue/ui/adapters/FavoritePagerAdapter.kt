package com.hendri.movie.catalogue.ui.adapters

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hendri.movie.catalogue.R
import com.hendri.movie.catalogue.ui.fragments.FavoriteMovieFragment
import com.hendri.movie.catalogue.ui.fragments.FavoriteTvShowFragment

class FavoritePagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment.newInstance(position + 1)
            else -> FavoriteTvShowFragment.newInstance(position + 1)
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show
        )
    }
}