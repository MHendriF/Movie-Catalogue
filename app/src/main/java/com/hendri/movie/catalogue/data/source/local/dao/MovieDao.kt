package com.hendri.movie.catalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailGenreMovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieWithGenre
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.*
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.utils.DataMapper.detailMovieResponseToEntity
import com.hendri.movie.catalogue.utils.DataMapper.movieResponseToEntity
import com.hendri.movie.catalogue.utils.DataMapper.movieToEntity

@Dao
abstract class MovieDao : BaseDao<MovieResponseEntity, MovieEntity,
        MovieResponseWithGenre, MovieWithGenre,
        GenreMovieEntity, DetailMovieResponseEntity, DetailGenreMovieEntity>() {

    companion object {
        private fun query(q: String, isFavorite: Boolean = false) =
            "SELECT * FROM MovieEntity ${if (isFavorite) "WHERE isFavorite = 1" else ""} ORDER BY $q ASC"

        val SORT_BY_NAME = SimpleSQLiteQuery(query("title"))
        val SORT_BY_RELEASE = SimpleSQLiteQuery(query("release_date"))
        val SORT_FAV_BY_NAME = SimpleSQLiteQuery(query("title", true))
        val SORT_FAV_BY_RELEASE = SimpleSQLiteQuery(query("release_date", true))
    }

    @Transaction
    @Query("SELECT * FROM MovieResponseEntity")
    abstract fun liveMovie(): LiveData<MovieResponseWithGenre>

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    abstract fun pageMovie(): DataSource.Factory<Int, MovieWithGenre>

    @Transaction
    @RawQuery(observedEntities = [MovieWithGenre::class])
    abstract fun pageMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieWithGenre>?

    @Query("SELECT * FROM MovieEntity WHERE id_movie=:id")
    abstract fun movie(id: Int): MovieEntity

    @Query("SELECT * FROM DetailMovieResponseEntity WHERE id_detail_movie_response=:id")
    abstract fun detailMovie(id: Int): DetailMovieResponseEntity

    @Transaction
    @Query("SELECT * FROM DetailMovieResponseEntity WHERE id_detail_movie_response=:id")
    abstract fun liveDetailMovie(id: Int): LiveData<DetailMovieWithGenre>

    @Transaction
    @RawQuery(observedEntities = [MovieWithGenre::class])
    abstract fun pageMovieFavorite(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieWithGenre>?

    fun insertMovie(response: MovieResponse) {
        val idInsertResponse = insertResponse(movieResponseToEntity(response))
        for (item in response.results) {
            val idInsertMovieResult = insertResult(movieToEntity(idInsertResponse, item))
            item.genre_ids?.forEach {
                insertGenre(GenreMovieEntity(fk = idInsertMovieResult, genre = it))
            }
        }
    }

    fun insertMovieDetail(response: DetailMovieResponse) {
        val idResult = insertDetailResponse(detailMovieResponseToEntity(response))
        response.genres.forEach {
            insertDetailGenre(
                DetailGenreMovieEntity(
                    genre_code = it.genre_code, fk = idResult, name = it.name
                )
            )
        }
    }
}