package com.hendri.movie.catalogue.ui.listeners

import com.hendri.movie.catalogue.data.response.TvShow

interface TvShowListener {
    fun onItemClicked(tvShow: TvShow)
}