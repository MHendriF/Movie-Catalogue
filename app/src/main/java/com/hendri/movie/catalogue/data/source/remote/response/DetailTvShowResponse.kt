package com.hendri.movie.catalogue.data.source.remote.response

import com.hendri.movie.catalogue.data.model.Genre

data class DetailTvShowResponse(
    val backdrop_path: String? = "",
    val episode_run_time: List<Int> = listOf(),
    val first_air_date: String? = "",
    val genres: List<Genre> = listOf(),
    val homepage: String? = "",
    val id: Int,
    val in_production: Boolean = false,
    val languages: List<String?> = listOf(),
    val last_air_date: String? = "",
    val name: String? = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String?> = listOf(),
    val original_language: String? = "",
    val original_name: String? = "",
    val overview: String? = "",
    val popularity: Double = 0.0,
    val poster_path: String? = "",
    val status: String? = "",
    val tagline: String? = "",
    val type: String? = "",
    val vote_average: Float = 0.0f,
    val vote_count: Int = 0
)