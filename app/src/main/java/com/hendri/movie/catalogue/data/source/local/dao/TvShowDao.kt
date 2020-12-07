package com.hendri.movie.catalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.model.DetailTvShow
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailGenreTvShowEntity
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowRelation
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowResponseEntity
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.*
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.DataMapper.tvDetailResponseToTvDetailEntity
import com.hendri.movie.catalogue.utils.DataMapper.tvResponseToTvShowResponseEntity
import com.hendri.movie.catalogue.utils.DataMapper.tvResultToTvShowEntity

@Dao
abstract class TvShowDao : BaseDao<TvShowResponseEntity, TvShowEntity,
            TvShowResponseRelation, TvShowGenreRelation,
            GenreTvShowEntity, DetailTvShowResponseEntity, DetailGenreTvShowEntity>() {

    companion object {
        private fun query(q: String, isFavorite: Boolean = false) =
            "SELECT * FROM TvShowEntity ${if (isFavorite) "WHERE isFavorite = 1" else ""} ORDER BY $q ASC"

        val SORT_BY_NAME = SimpleSQLiteQuery(query("original_name"))
        val SORT_BY_RELEASE = SimpleSQLiteQuery(query("first_air_date"))
        val SORT_FAV_BY_NAME = SimpleSQLiteQuery(query("original_name", true))
        val SORT_FAV_BY_RELEASE = SimpleSQLiteQuery(query("first_air_date", true))
    }

    @Transaction
    @Query("SELECT * FROM TvShowResponseEntity")
    abstract fun liveTv(): LiveData<TvShowResponseRelation>

    @Transaction
    @Query("SELECT * FROM TvShowEntity")
    abstract fun pageTvResult(): DataSource.Factory<Int, TvShowGenreRelation>

    @Transaction
    @RawQuery(observedEntities = [TvShowGenreRelation::class])
    abstract fun pageTvResult(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowGenreRelation>?

    @Query("SELECT * FROM TvShowEntity WHERE id_tv_result=:id")
    abstract fun tvResult(id: Int): TvShowEntity

    @Transaction
    @Query("SELECT * FROM DetailTvShowResponseEntity WHERE pk_id_tv_detail_response=:id")
    abstract fun liveTvDetail(id: Int): LiveData<DetailTvShowRelation>

    @Query("SELECT * FROM DetailTvShowResponseEntity WHERE pk_id_tv_detail_response=:id")
    abstract fun tvDetail(id: Int): DetailTvShowResponseEntity

    @Transaction
    @RawQuery(observedEntities = [TvShowGenreRelation::class])
    abstract fun pageTvFavorite(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowGenreRelation>?

    fun insertTv(tvResponse: TvShowResponse) {
        val fk = insertResponse(tvResponseToTvShowResponseEntity(tvResponse))
        for (tvResult in tvResponse.results) {
            val idInsertTvResult = insertResult(tvResultToTvShowEntity(fk, tvResult))
            tvResult.genre_ids.forEach {
                insertGenre(GenreTvShowEntity(fk = idInsertTvResult, genre = it))
            }
        }
    }

    fun insertTvDetail(tvDetailResponse: DetailTvShowResponse) {
        val fk = insertDetailResponse(tvDetailResponseToTvDetailEntity(tvDetailResponse))
        tvDetailResponse.genres.forEach {
            insertDetailGenre(
                DetailGenreTvShowEntity(
                    genre_code = it.genre_code,
                    fk = fk,
                    name = it.name
                )
            )
        }
    }
}