package com.hendri.movie.catalogue.data.source.local.entity.detail.movie

import androidx.room.Embedded
import androidx.room.Relation

data class DetailMovieWithGenre (
    @Embedded
    val entity: DetailMovieResponseEntity,

    @Relation(
        parentColumn = DetailMovieResponseEntity.PRIMARY_KEY,
        entityColumn = DetailGenreMovieEntity.FOREIGN_KEY,
        entity = DetailGenreMovieEntity::class
    )
    val genre: List<DetailGenreMovieEntity>
)