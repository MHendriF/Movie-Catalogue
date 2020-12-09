package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import androidx.room.Embedded
import androidx.room.Relation

data class TvShowResponseWithGenre(
    @Embedded
    val entity: TvShowResponseEntity,

    @Relation(
        parentColumn = TvShowResponseEntity.PRIMARY_KEY,
        entityColumn = TvShowEntity.FOREIGN_KEY,
        entity = TvShowEntity::class
    )
    val result: List<TvShowWithGenre>
)