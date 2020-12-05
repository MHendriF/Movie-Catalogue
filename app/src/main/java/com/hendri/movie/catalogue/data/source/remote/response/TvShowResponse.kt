package com.hendri.movie.catalogue.data.source.remote.response

import com.hendri.movie.catalogue.data.source.remote.response.models.TvShow

data class TvShowResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<TvShow>
)