package com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [DetailGenreTvShowEntity.FK])],
    foreignKeys = [ForeignKey(
        entity = DetailTvShowResponseEntity::class,
        parentColumns = [DetailTvShowResponseEntity.PK],
        childColumns = [DetailGenreTvShowEntity.FK],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)

data class DetailGenreTvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "pk_tv_detail_genre")
    val pk: Long? = null,

    @NonNull
    @ColumnInfo(name = FK)
    val fk: Long,

    val genre_code: Int,
    val name: String
) : Parcelable {
    companion object {
        const val FK = "fk_tv_detail_genre"
    }
}