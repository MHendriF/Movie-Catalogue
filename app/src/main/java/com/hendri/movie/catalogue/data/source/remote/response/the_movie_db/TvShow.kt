package com.hendri.movie.catalogue.data.source.remote.response.the_movie_db

data class TvShow(
    val original_name: String = "",
    val genre_ids: List<Int>,
    val name: String = "",
    val popularity: Double = 0.0,
    val origin_country: List<String>,
    val vote_count: Int = 0,
    val first_air_date: String = "",
    val backdrop_path: String = "",
    val original_language: String = "",
    val id: Int,
    val vote_average: Float = 0.0f,
    val overview: String = "",
    val poster_path: String = ""
)