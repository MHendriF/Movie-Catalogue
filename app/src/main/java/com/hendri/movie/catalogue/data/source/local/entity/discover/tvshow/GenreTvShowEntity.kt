package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [GenreTvShowEntity.FOREIGN_KEY])],
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = [TvShowEntity.PRIMARY_KEY],
        childColumns = [GenreTvShowEntity.FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)
class GenreTvShowEntity(
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

) : Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_genre_tv_show"
        const val FOREIGN_KEY = "id_genre_tv_show_foreign"
    }
}