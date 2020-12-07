package com.hendri.movie.catalogue.data.source.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<ResponseEntity, ResultEntity, ResponseWithResult, ResultWithGenre, GenreEntity, DetailResponseEntity, DetailGenreEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertResponse(responseEntity: ResponseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertResult(resultEntity: ResultEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertGenre(genreEntity: GenreEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertDetailResponse(detailResponseEntity: DetailResponseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDetailGenre(detailGenreEntity: DetailGenreEntity): Long

    @Update
    abstract fun updateResultFavorite(resultEntity: ResultEntity)

    @Update
    abstract fun updateDetailFavorite(detailResponseEntity: DetailResponseEntity)
}