package com.hendri.movie.catalogue.data.source.remote.response.models

data class TvShow(
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val genre_ids: List<Int>,
    val id: Int,
    val name: String = "",
    val origin_country: List<String>,
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val vote_average: Float = 0.0f,
    val vote_count: Int = 0
)