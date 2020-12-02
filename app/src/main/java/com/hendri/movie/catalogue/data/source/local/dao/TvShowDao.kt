package com.hendri.movie.catalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hendri.movie.catalogue.data.source.local.entity.TvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvShows")
    fun getTvShows(): LiveData<List<TvShow>>

    @Transaction
    @Query("SELECT * FROM tvShows WHERE id = :id")
    fun getTvShowDetail(id: Int): LiveData<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShow>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTvShow(tvShow: TvShow)
}