package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.room.*
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [TvShowEntity.ID_TV_SHOW_FOREIGN])],
    foreignKeys = [ForeignKey(
        entity = TvShowResponseEntity::class,
        parentColumns = [TvShowResponseEntity.ID_TV_SHOW_RESPONSE],
        childColumns = [TvShowEntity.ID_TV_SHOW_FOREIGN],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
)

data class TvShowEntity(
    @PrimaryKey
    @ColumnInfo(name = ID_TV_SHOW)
    val pk: Int,
    @ColumnInfo(name = ID_TV_SHOW_FOREIGN)
    val fk: Long,
    var isFavorite: Boolean = false,
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