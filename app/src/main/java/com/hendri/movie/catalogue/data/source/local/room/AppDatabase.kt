package com.hendri.movie.catalogue.data.source.local.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailGenreMovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailGenreTvShowEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.GenreMovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.MovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.GenreTvShowEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowResponseEntity
import com.hendri.movie.catalogue.utils.Constants

@Database(
    entities = [
        MovieEntity::class,
        MovieResponseEntity::class,
        GenreMovieEntity::class,
        DetailMovieResponseEntity::class,
        DetailGenreMovieEntity::class,

        TvShowEntity::class,
        TvShowResponseEntity::class,
        GenreTvShowEntity::class,
        DetailTvShowResponseEntity::class,
        DetailGenreTvShowEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun tvShowDao(): TvShowDao
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            application, AppDatabase::class.java, Constants.DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE as AppDatabase
        }
    }
}