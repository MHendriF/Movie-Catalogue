package com.hendri.movie.catalogue.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hendri.movie.catalogue.data.local.dao.MovieDao
import com.hendri.movie.catalogue.data.local.dao.TvShowDao
import com.hendri.movie.catalogue.data.response.Movie
import com.hendri.movie.catalogue.data.response.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun tvShowDao() : TvShowDao
}