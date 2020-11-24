package com.hendri.movie.catalogue.ui.listeners

import com.hendri.movie.catalogue.data.DataEntity
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.TvShow

interface ItemListener {
    fun onItemClicked(dataEntity: DataEntity)

    fun onItemClicked(movie: Movie)

    fun onItemClicked(tvShow: TvShow)
}