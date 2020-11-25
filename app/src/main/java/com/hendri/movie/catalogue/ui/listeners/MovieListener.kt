package com.hendri.movie.catalogue.ui.listeners

import com.hendri.movie.catalogue.data.response.Movie

interface MovieListener {
    fun onItemClicked(movie: Movie)
}