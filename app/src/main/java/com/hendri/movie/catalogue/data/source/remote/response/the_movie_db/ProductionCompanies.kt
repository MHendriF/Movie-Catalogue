package com.hendri.movie.catalogue.data.source.remote.response.the_movie_db

data class ProductionCompanies(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)