package com.hendri.movie.catalogue.data.source.local.entity.detail.movie

import androidx.room.Embedded
import androidx.room.Relation

data class DetailMovieRelation (
    @Embedded
    val movieDetailResponseEntity: DetailMovieResponseEntity,

    @Relation(
        parentColumn = DetailMovieResponseEntity.PK,
        entityColumn = DetailGenreMovieEntity.FOREIGN_KEY,
        entity = DetailGenreMovieEntity::class
    )
    val genre: List<DetailGenreMovieEntity>
)