package com.hendri.movie.catalogue.data.source.local

import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao.Companion.SORT_BY_NAME
import com.hendri.movie.catalogue.data.source.local.dao.TvShowDao.Companion.SORT_FAV_BY_NAME
import com.hendri.movie.catalogue.data.source.local.entity.detail.tvshow.DetailTvShowWithGenre
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowWithGenre
import com.hendri.movie.catalogue.data.source.local.entity.discover.tvshow.TvShowResponseWithGenre
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse

class TvShowDataSource private constructor(private val tvShowDao: TvShowDao) :
    IMainDataSource<TvShowResponse, DetailTvShowResponse, TvShowResponseWithGenre, TvShowWithGenre, DetailTvShowWithGenre> {

    companion object {
        @Volatile
        private var instance: TvShowDataSource? = null

        fun getInstance(tvShowDao: TvShowDao): TvShowDataSource =
            instance ?: synchronized(this) { instance ?: TvShowDataSource(tvShowDao) }
    }

    override fun getResponse() = tvShowDao.liveTvShow()

    override fun getResult() = tvShowDao.pageTvShow()

    override fun getResultRawQuery(supportSQLiteQuery: SupportSQLiteQuery?) =
        tvShowDao.pageTvShow(supportSQLiteQuery ?: run { SORT_BY_NAME })

    override fun getDetail(id: Int) = tvShowDao.liveDetailTvShow(id)

    override fun insertResponse(data: TvShowResponse) = tvShowDao.insertTvShow(data)

    override fun insertDetailResponse(data: DetailTvShowResponse) = tvShowDao.insertDetailTvShow(data)

    override fun updateFavorite(id: Int, isFavorite: Boolean) {
        tvShowDao.updateDetailFavorite(tvShowDao.detailTvShow(id).apply { this.isFavorite = isFavorite })
        tvShowDao.updateResultFavorite(tvShowDao.tvShow(id).apply { this.isFavorite = isFavorite })
    }

    override fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?) =
        tvShowDao.pageTvShowFavorite(supportSQLiteQuery ?: run { SORT_FAV_BY_NAME })
}