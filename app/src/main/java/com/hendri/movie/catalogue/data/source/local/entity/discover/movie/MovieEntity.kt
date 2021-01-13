package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [MovieEntity.FOREIGN_KEY])],
    foreignKeys = [ForeignKey(
        entity = MovieResponseEntity::class,
        parentColumns = [MovieResponseEntity.PRIMARY_KEY],
        childColumns = [MovieEntity.FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
)

data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val pk: Int,

    @NonNull
    @ColumnInfo(name = FOREIGN_KEY)
    val fk: Long,

    var isFavorite: Boolean = false,
    val popularity: Double = 0.0,
    val vote_count: Int = 0,
    val video: Boolean = false,
    val poster_path: String = "",
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val title: String = "",
    val vote_average: Float = 0.0f,
    val overview: String = "",
    val release_date: String = ""
) : Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_movie"
        const val FOREIGN_KEY = "id_movie_foreign"
    }
}