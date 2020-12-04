package com.hendri.movie.catalogue.data.source.remote.response

import com.hendri.movie.catalogue.data.source.remote.response.the_movie_db.TvResult

data class TvResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<TvResult>
)