package com.hendri.movie.catalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.model.DetailMovie
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailGenreMovieEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieRelation
import com.hendri.movie.catalogue.data.source.local.entity.detail.movie.DetailMovieResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.movie.*
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.utils.DataMapper.movieDetailResponseToMovieDetailResponseEntity
import com.hendri.movie.catalogue.utils.DataMapper.movieResponseToMovieResponseEntity
import com.hendri.movie.catalogue.utils.DataMapper.movieResultToMovieEntity

@Dao
abstract class MovieDao : BaseDao<MovieResponseEntity, MovieEntity,
        MovieResponseRelation, MovieGenreRelation,
        GenreMovieEntity, DetailMovieResponseEntity, DetailGenreMovieEntity>() {

    companion object {
        private fun query(q: String, isFavorite: Boolean = false) =
            "SELECT * FROM MovieResultEntity ${if (isFavorite) "WHERE isFavorite = 1" else ""} ORDER BY $q ASC"

        val SORT_BY_NAME = SimpleSQLiteQuery(query("title"))
        val SORT_BY_RELEASE = SimpleSQLiteQuery(query("release_date"))
        val SORT_FAV_BY_NAME = SimpleSQLiteQuery(query("title", true))
        val SORT_FAV_BY_RELEASE = SimpleSQLiteQuery(query("release_date", true))
    }

    @Transaction
    @Query("SELECT * FROM MovieResponseEntity")
    abstract fun liveMovie(): LiveData<MovieResponseRelation>

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    abstract fun pageMovie(): DataSource.Factory<Int, MovieGenreRelation>

    @Transaction
    @RawQuery(observedEntities = [MovieGenreRelation::class])
    abstract fun pageMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieGenreRelation>?

    @Query("SELECT * FROM MovieEntity WHERE id_movie_result=:id")
    abstract fun movie(id: Int): MovieEntity

    @Query("SELECT * FROM DetailMovieResponseEntity WHERE pk_movie_detail_response=:id")
    abstract fun detailMovie(id: Int): DetailMovieResponseEntity

    @Transaction
    @Query("SELECT * FROM DetailMovieResponseEntity WHERE pk_movie_detail_response=:id")
    abstract fun liveDetailMovie(id: Int): LiveData<DetailMovieRelation>

    @Transaction
    @RawQuery(observedEntities = [MovieGenreRelation::class])
    abstract fun pageMovieFavorite(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieGenreRelation>?

    fun insertMovie(response: MovieResponse) {
        val idInsertResponse = insertResponse(movieResponseToMovieResponseEntity(response))
        for (item in response.results) {
            val idInsertMovieResult = insertResult(movieResultToMovieEntity(idInsertResponse, item))
            item.genre_ids?.forEach {
                insertGenre(GenreMovieEntity(fk = idInsertMovieResult, genre = it))
            }
        }
    }

    fun insertMovieDetail(response: DetailMovieResponse) {
        val idResult = insertDetailResponse(movieDetailResponseToMovieDetailResponseEntity(response))
        response.genres.forEach {
            insertDetailGenre(
                DetailGenreMovieEntity(
                    genre_code = it.genre_code, fk = idResult, name = it.name
                )
            )
        }
    }
}