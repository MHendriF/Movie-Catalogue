package com.hendri.movie.catalogue.data.source.local.entity.discover.movie

import androidx.room.Embedded
import androidx.room.Relation

class MovieGenreRelation(
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = MovieEntity.ID_MOVIE_RESULT,
        entityColumn = GenreMovieEntity.FOREIGN_KEY_MOVIE_GENRE,
        entity = GenreMovieEntity::class
    )
    val genreIds: List<GenreMovieEntity>
)