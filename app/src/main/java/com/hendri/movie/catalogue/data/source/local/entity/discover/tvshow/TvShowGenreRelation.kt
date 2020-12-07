package com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow

import androidx.room.Embedded
import androidx.room.Relation

data class TvShowGenreRelation(
    @Embedded
    val tvShowEntity: TvShowEntity,

    @Relation(
        parentColumn = TvShowEntity.ID_TV_SHOW,
        entityColumn = GenreTvShowEntity.FOREIGN_KEY_TV_SHOW_GENRE,
        entity = GenreTvShowEntity::class
    )
    val genreIds: List<GenreTvShowEntity>
)