package com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTvShowWithGenre(
    @Embedded
    val entity: DetailTvShowResponseEntity,

    @Relation(
        parentColumn = DetailTvShowResponseEntity.PRIMARY_KEY,
        entityColumn = DetailGenreTvShowEntity.FOREIGN_KEY,
        entity = DetailGenreTvShowEntity::class
    )
    val genre: List<DetailGenreTvShowEntity>
) : Parcelable