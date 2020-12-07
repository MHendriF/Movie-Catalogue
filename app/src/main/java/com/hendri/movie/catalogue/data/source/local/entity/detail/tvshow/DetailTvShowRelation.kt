package com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailTvShowRelation(
    @Embedded
    val tvDetailResponseEntity: DetailTvShowResponseEntity,

    @Relation(
        parentColumn = DetailTvShowResponseEntity.PK,
        entityColumn = DetailGenreTvShowEntity.FK,
        entity = DetailGenreTvShowEntity::class
    )
    val genre: List<DetailGenreTvShowEntity>
) : Parcelable