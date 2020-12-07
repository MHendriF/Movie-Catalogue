package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import androidx.room.Embedded
import androidx.room.Relation

data class TvShowResponseRelation(
    @Embedded
    val tvShowResponseEntity: TvShowResponseEntity,

    @Relation(
        parentColumn = TvShowResponseEntity.ID_TV_SHOW_RESPONSE,
        entityColumn = TvShowEntity.ID_TV_SHOW_FOREIGN,
        entity = TvShowEntity::class
    )
    val result: List<TvShowGenreRelation>
)