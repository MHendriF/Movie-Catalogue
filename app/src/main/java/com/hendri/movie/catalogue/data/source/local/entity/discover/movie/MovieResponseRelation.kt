package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieResponseRelation(
    @Embedded
    val movieResponseEntity: MovieResponseEntity,

    @Relation(
        parentColumn = MovieResponseEntity.ID_MOVIE_RESPONSE,
        entityColumn = MovieEntity.ID_MOVIE_RESULT_FOREIGN,
        entity = MovieEntity::class
    )
    val resultWithGenre: List<MovieGenreRelation>
)