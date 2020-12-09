package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [GenreMovieEntity.FOREIGN_KEY])],
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = [MovieEntity.PRIMARY_KEY],
        childColumns = [GenreMovieEntity.FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)

class GenreMovieEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val pk: Int? = null,

    @NonNull
    @ColumnInfo(name = FOREIGN_KEY)
    val fk: Long,

    @NonNull
    @ColumnInfo(name = "genre_code")
    val genre: Int
): Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_genre_movie"
        const val FOREIGN_KEY = "id_genre_movie_foreign"
    }
}