package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    indices = [
        Index(value = ["page"], unique = true)
    ]
)

data class TvShowResponseEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = PRIMARY_KEY)
    val id_tv_show_response: Int? = null,

    val page: Int = 0,

    val total_results: Int = 0,

    val total_pages: Int = 0,

    ) : Parcelable {
    companion object {
        const val PRIMARY_KEY = "id_tv_show_response"
    }
}