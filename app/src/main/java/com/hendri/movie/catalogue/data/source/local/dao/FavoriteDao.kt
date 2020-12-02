package com.hendri.movie.catalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hendri.movie.catalogue.data.source.local.entity.Favorite
import androidx.paging.DataSource

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(item: Favorite)

    @Delete
    fun deleteFavorite(item: Favorite)

    @Transaction
    @Query("SELECT * FROM favorites WHERE type = :type")
    fun getFavoriteByType(type: String): DataSource.Factory<Int, Favorite>

    @Transaction
    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteById(id: Int): LiveData<Favorite>
}