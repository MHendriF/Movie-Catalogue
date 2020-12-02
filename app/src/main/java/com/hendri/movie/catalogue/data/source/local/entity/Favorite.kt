package com.hendri.movie.catalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hendri.movie.catalogue.utils.Constants

@Entity(tableName = Constants.FAVORITE_TABLE_NAME)
data class Favorite(
    @PrimaryKey
    @NonNull
    var id: Int,

    var imgPoster: String? = null,

    var type: String? = null
)