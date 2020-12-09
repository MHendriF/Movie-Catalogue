package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithGenre(
    @Embedded
    val entity: MovieEntity,

    @Relation(
        parentColumn = MovieEntity.PRIMARY_KEY,
        entityColumn = GenreMovieEntity.FOREIGN_KEY,
        entity = GenreMovieEntity::class
    )
    val genreIds: List<GenreMovieEntity>
)