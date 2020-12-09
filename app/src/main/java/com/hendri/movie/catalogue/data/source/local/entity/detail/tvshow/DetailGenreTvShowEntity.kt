package com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [DetailGenreTvShowEntity.FOREIGN_KEY])],
    foreignKeys = [ForeignKey(
        entity = DetailTvShowResponseEntity::class,
        parentColumns = [DetailTvShowResponseEntity.PRIMARY_KEY],
        childColumns = [DetailGenreTvShowEntity.FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)

data class DetailGenreTvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val pk: Long? = null,

    @NonNull
    @ColumnInfo(name = FOREIGN_KEY)
    val fk: Long,

    val genre_code: Int,
    val name: String
) : Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_detail_genre_tv_show"
        const val FOREIGN_KEY = "id_detail_genre_tv_show_foreign"
    }
}