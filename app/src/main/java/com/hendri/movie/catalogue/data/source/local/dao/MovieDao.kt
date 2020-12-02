package com.hendri.movie.catalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hendri.movie.catalogue.data.source.local.entity.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<Movie>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)
}