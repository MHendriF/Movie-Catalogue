package com.hendri.movie.catalogue.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hendri.movie.catalogue.data.source.local.dao.FavoriteDao
import com.hendri.movie.catalogue.data.source.local.dao.MovieDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao
import com.hendri.movie.catalogue.data.source.local.entity.Favorite
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.local.entity.TvShow
import com.hendri.movie.catalogue.utils.Constants

@Database(
    entities = [Movie::class, TvShow::class, Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DATABASE_NAME
                ).allowMainThreadQueries().build()
            }

    }
}