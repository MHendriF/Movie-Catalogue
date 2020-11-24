package com.hendri.movie.catalogue.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hendri.movie.catalogue.data.response.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<Movie>
}