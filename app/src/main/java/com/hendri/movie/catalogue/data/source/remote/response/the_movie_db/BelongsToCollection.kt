package com.hendri.movie.catalogue.data.source.remote.response.the_movie_db

data class BelongsToCollection(
	val id: Int,
	val name: String,
	val poster_path: String,
	val backdrop_path: String
)