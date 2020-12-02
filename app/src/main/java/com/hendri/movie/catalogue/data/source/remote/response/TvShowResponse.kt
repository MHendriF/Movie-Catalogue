package com.hendri.movie.catalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("poster_path")
	val posterPath: String?,

	@field:SerializedName("backdrop_path")
	val backdropPath: String?,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("vote_count")
	val voteCount: Int
)

