package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import androidx.room.Embedded
import androidx.room.Relation

class TvShowGenreRelation(
    @Embedded
    val tvResultEntity: TvShowEntity,

    @Relation(
        parentColumn = TvShowEntity.ID_TV_RESULT,
        entityColumn = GenreTvShowEntity.FK,
        entity = GenreTvShowEntity::class
    )
    val genreIds: List<GenreTvShowEntity>
)