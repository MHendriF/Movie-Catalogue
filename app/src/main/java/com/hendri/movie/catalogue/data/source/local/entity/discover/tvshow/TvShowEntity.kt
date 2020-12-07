package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    primaryKeys = [TvShowEntity.ID_TV_SHOW],
    indices = [Index(value = [TvShowEntity.ID_TV_SHOW_FOREIGN])],
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = [TvShowResponseEntity.ID_TV_SHOW_RESPONSE],
        childColumns = [TvShowEntity.ID_TV_SHOW_FOREIGN],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
)

data class TvShowEntity(
    var isFavorite: Boolean = false,
    val id_tv_show: Int,
    val id_tv_show_foreign: Long,
    val original_name: String = "",
    val name: String = "",
    val popularity: Double = 0.0,
    val vote_count: Int = 0,
    val first_air_date: String = "",
    val backdrop_path: String = "",
    val original_language: String = "",
    val vote_average: Float = 0.0f,
    val overview: String = "",
    val poster_path: String = ""
) : Parcelable {
    companion object {
        const val ID_TV_SHOW_FOREIGN = "id_tv_show_foreign"
        const val ID_TV_SHOW = "id_tv_show"
    }
}