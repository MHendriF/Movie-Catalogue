package com.hendri.movie.catalogue.ui.listeners

import com.hendri.movie.catalogue.data.source.local.entity.Movie

interface MovieListener {
    fun onItemClicked(movie: Movie)
}