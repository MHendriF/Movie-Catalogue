package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [GenreMovieEntity.FOREIGN_KEY_MOVIE_GENRE])],
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = [MovieEntity.ID_MOVIE_RESULT],
        childColumns = [GenreMovieEntity.FOREIGN_KEY_MOVIE_GENRE],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)

class GenreMovieEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk_genre")
    val pk: Int? = null,

    @ColumnInfo(name = "foreign_key_movie_genre")
    val fk: Long,

    @ColumnInfo(name = "genre_code")
    val genre: Int
): Parcelable {
    companion object {
        const val FOREIGN_KEY_MOVIE_GENRE = "foreign_key_movie_genre"
    }
}