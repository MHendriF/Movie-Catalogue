package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [MovieEntity.ID_MOVIE_RESULT_FOREIGN])],
    foreignKeys = [ForeignKey(
        entity = MovieResponseEntity::class,
        parentColumns = [MovieResponseEntity.ID_MOVIE_RESPONSE],
        childColumns = [MovieEntity.ID_MOVIE_RESULT_FOREIGN],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
)

class MovieEntity (
    @PrimaryKey
    @ColumnInfo(name = ID_MOVIE_RESULT)
    val pk: Int,
    @ColumnInfo(name = ID_MOVIE_RESULT_FOREIGN)
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
        const val ID_MOVIE_RESULT_FOREIGN = "id_movie_result_foreign"
        const val ID_MOVIE_RESULT = "id_movie_result"
    }
}