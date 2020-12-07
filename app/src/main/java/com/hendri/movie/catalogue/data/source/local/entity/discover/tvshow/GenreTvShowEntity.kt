package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [GenreTvShowEntity.FOREIGN_KEY_TV_SHOW_GENRE])],
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = [TvShowEntity.ID_TV_SHOW],
        childColumns = [GenreTvShowEntity.FOREIGN_KEY_TV_SHOW_GENRE],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)
class GenreTvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pk_tv_show_genre")
    val pk: Int? = null,

    @ColumnInfo(name = FOREIGN_KEY_TV_SHOW_GENRE)
    val fk: Long,

    val genre: Int

) : Parcelable {
    companion object {
        const val FOREIGN_KEY_TV_SHOW_GENRE = "foreign_tv_show_genre"
    }
}