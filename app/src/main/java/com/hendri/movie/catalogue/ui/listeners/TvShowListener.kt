package com.hendri.movie.catalogue.ui.listeners

import com.hendri.movie.catalogue.data.source.local.entity.TvShow

interface TvShowListener {
    fun onItemClicked(tvShow: TvShow)
}