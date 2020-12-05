package com.hendri.movie.catalogue.binding

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.hendri.movie.catalogue.data.source.remote.response.the_movie_db.Genres

object DetailBindingAdapter {
    @JvmStatic
    @BindingAdapter("set_genre")
    fun setGenre(view: AppCompatTextView, it: List<Genres>?) {
        val genre = StringBuilder()
        it?.forEachIndexed { i, v ->
            genre.append(if (i != it.size - 1) "${v.name}, " else v.name)
        }
        view.text = genre.toString()
    }

    private fun setColorDrawable(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}