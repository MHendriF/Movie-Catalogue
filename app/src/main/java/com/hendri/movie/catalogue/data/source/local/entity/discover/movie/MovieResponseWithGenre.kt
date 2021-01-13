package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieResponseWithGenre(
    @Embedded
    val entity: MovieResponseEntity,

    @Relation(
        parentColumn = MovieResponseEntity.PRIMARY_KEY,
        entityColumn = MovieEntity.FOREIGN_KEY,
        entity = MovieEntity::class
    )
    val resultWithWithGenre: List<MovieWithGenre>
)