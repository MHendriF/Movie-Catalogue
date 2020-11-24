package com.hendri.movie.catalogue.data.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hendri.movie.catalogue.utils.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowResponse(
	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TvShow>?,

	@field:SerializedName("total_results")
	val totalResults: Int
) : Parcelable

@Entity(tableName = Constants.TV_SHOW_TABLE_NAME)
@Parcelize
data class TvShow(
	@PrimaryKey(autoGenerate = true) val uid: Int,

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

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int
) : Parcelable
