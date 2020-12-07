package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [GenreTvShowEntity.FK])],
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = [TvShowEntity.ID_TV_RESULT],
        childColumns = [GenreTvShowEntity.FK],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)
class GenreTvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk_tv_genre")
    val pk: Int? = null,

    @ColumnInfo(name = FK)
    val fk: Long,

    val genre: Int

) : Parcelable {
    companion object {
        const val FK = "foreign_tv_genre"
    }
}