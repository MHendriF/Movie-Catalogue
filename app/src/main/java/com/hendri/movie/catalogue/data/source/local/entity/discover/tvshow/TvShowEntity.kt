package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    indices = [Index(value = [TvShowEntity.FOREIGN_KEY])],
    foreignKeys = [ForeignKey(
        entity = TvShowResponseEntity::class,
        parentColumns = [TvShowResponseEntity.PRIMARY_KEY],
        childColumns = [TvShowEntity.FOREIGN_KEY],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
)

data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val pk: Int,

    @ColumnInfo(name = FOREIGN_KEY)
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
        const val PRIMARY_KEY = "id_tv_show"
        const val FOREIGN_KEY = "id_tv_show_foreign"
    }
}