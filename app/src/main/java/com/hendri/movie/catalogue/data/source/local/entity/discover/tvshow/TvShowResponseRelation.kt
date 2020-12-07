package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import androidx.room.Embedded
import androidx.room.Relation

class TvShowResponseRelation(
    @Embedded
    val tvResponseEntity: TvShowResponseEntity,

    @Relation(
        parentColumn = TvShowResponseEntity.ID_TV_RESPONSE,
        entityColumn = TvShowEntity.ID_TV_RESULT_FOREIGN,
        entity = TvShowEntity::class
    )
    val result: List<TvShowGenreRelation>
)