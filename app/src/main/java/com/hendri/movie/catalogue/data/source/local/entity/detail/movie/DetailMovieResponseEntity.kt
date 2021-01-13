package com.hendri.movie.catalogue.data.source.local.entity.detail.movie

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DetailMovieResponseEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val pk: Int,
    var isFavorite: Boolean = false,
    val adult: Boolean = false,
    val backdrop_path: String? = "",
    val budget: Int = 0,
    val homepage: String? = "",
    val imdb_id: String? = "",
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean = false,
    val vote_average: Float = 0.0f,
    val vote_count: Int = 0
) : Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_detail_movie_response"
    }
}