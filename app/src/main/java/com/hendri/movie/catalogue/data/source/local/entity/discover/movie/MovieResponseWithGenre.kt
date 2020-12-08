package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieResponseWithGenre(
    @Embedded
    val movieResponseEntity: MovieResponseEntity,

    @Relation(
        parentColumn = MovieResponseEntity.ID_MOVIE_RESPONSE,
        entityColumn = MovieEntity.ID_MOVIE_FOREIGN,
        entity = MovieEntity::class
    )
    val resultWithWithGenre: List<MovieWithGenre>
)