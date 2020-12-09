package com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DetailTvShowResponseEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val pk: Int,
    var isFavorite: Boolean = false,
    val backdrop_path: String? = "",
    val first_air_date: String? = "",
    val homepage: String? = "",
    val in_production: Boolean = false,
    val last_air_date: String? = "",
    val name: String? = "",
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val original_language: String? = "",
    val original_name: String? = "",
    val overview: String? = "",
    val popularity: Double = 0.0,
    val poster_path: String? = "",
    val status: String? = "",
    val type: String? = "",
    val tagline: String? = "",
    val vote_average: Float = 0.0f,
    val vote_count: Int = 0
) : Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_detail_tv_show_response"
    }
}