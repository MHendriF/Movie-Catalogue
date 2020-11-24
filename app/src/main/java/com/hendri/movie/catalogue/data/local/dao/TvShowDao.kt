package com.hendri.movie.catalogue.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hendri.movie.catalogue.data.response.TvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvShows")
    suspend fun getAll(): List<TvShow>
}