package com.hendri.movie.catalogue.data.source.local.entity.detail.movie

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [DetailGenreMovieEntity.FOREIGN_KEY])],
    foreignKeys = [ForeignKey(
        entity = DetailMovieResponseEntity::class,
        parentColumns = [DetailMovieResponseEntity.PK],
        childColumns = [DetailGenreMovieEntity.FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)

data class DetailGenreMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk_movie_detail_genre")
    val pk: Long? = null,

    val genre_code: Int,

    @ColumnInfo(name = FOREIGN_KEY)
    val fk: Long,

    val name: String
) : Parcelable {
    companion object {
        const val FOREIGN_KEY = "id_movie_detail_genre_foreign"
    }
}