package com.hendri.movie.catalogue.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao.Companion.SORT_BY_NAME
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao.Companion.SORT_FAV_BY_NAME
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowGenreRelation
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowResponseRelation
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse

class TvShowDataSource private constructor(private val tvShowDao: TvShowDao) :
    IMainDataSource<TvShowResponse, DetailTvShowResponse, TvShowResponseRelation, TvShowGenreRelation, DetailTvShowRelation> {

    companion object {
        @Volatile
        private var instance: TvShowDataSource? = null

        fun getInstance(tvShowDao: TvShowDao): TvShowDataSource =
            instance ?: synchronized(this) { instance ?: TvShowDataSource(tvShowDao) }
    }

    override fun getResponse() = tvShowDao.liveTv()

    override fun getResult() = tvShowDao.pageTvResult()

    override fun getResultRawQuery(supportSQLiteQuery: SupportSQLiteQuery?) =
        tvShowDao.pageTvResult(supportSQLiteQuery ?: run { SORT_BY_NAME })

    override fun getDetail(id: Int) = tvShowDao.liveTvDetail(id)

    override fun insertResponse(data: TvShowResponse) = tvShowDao.insertTv(data)

    override fun insertDetailResponse(data: DetailTvShowResponse) = tvShowDao.insertTvDetail(data)

    override fun updateFavorite(id: Int, isFavorite: Boolean) {
        tvShowDao.updateDetailFavorite(tvShowDao.tvDetail(id).apply { this.isFavorite = isFavorite })
        tvShowDao.updateResultFavorite(tvShowDao.tvResult(id).apply { this.isFavorite = isFavorite })
    }

    override fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?) =
        tvShowDao.pageTvFavorite(supportSQLiteQuery ?: run { SORT_FAV_BY_NAME })
}