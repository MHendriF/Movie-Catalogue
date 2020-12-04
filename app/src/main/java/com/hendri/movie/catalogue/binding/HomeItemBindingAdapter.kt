package com.hendri.movie.catalogue.binding

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter

object HomeItemBindingAdapter {
    val genres: MutableMap<Int, String> = mutableMapOf()

    init {
        genres[28] = "Action"
        genres[12] = "Adventure"
        genres[16] = "Animation"
        genres[35] = "Comedy"
        genres[80] = "Crime"
        genres[99] = "Documentary"
        genres[18] = "Drama"
        genres[10751] = "Family"
        genres[14] = "Fantasy"
        genres[36] = "History"
        genres[27] = "Horror"
        genres[10402] = "Music"
        genres[9648] = "Mystery"
        genres[10749] = "Romance"
        genres[878] = "Science Fiction"
        genres[10770] = "TV Movie"
        genres[53] = "Thriller"
        genres[10752] = "War"
        genres[37] = "Western"
        genres[10759] = "Action & Adventure"
        genres[10762] = "Kids"
        genres[10763] = "News"
        genres[10764] = "Reality"
        genres[10765] = "Sci-Fi & Fantasy"
        genres[10766] = "Soap"
        genres[10767] = "Talk"
        genres[10768] = "War & Politics"
    }

    @JvmStatic
    @BindingAdapter("set_home_item_genre")
    fun setTextGenre(view: AppCompatTextView, idListGenre: List<Int>?) {
        val genre = StringBuilder()
        idListGenre?.forEachIndexed { index, key ->
            genre.append(if (index != idListGenre.size - 1) "${genres[key]}, " else genres[key])
        }
        view.text = genre.toString()
    }
}