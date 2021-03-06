package com.hendri.movie.catalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.NetworkBoundResource
import com.hendri.movie.catalogue.data.model.DetailTvShow
import com.hendri.movie.catalogue.data.model.TvShow
import com.hendri.movie.catalogue.data.source.local.TvShowDataSource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailTvShowResponse
import com.hendri.movie.catalogue.data.source.remote.response.TvShowResponse
import com.hendri.movie.catalogue.utils.DataMapper
import com.hendri.movie.catalogue.utils.Executors

class TvShowRepositoryFake(
    private val executors: Executors,
    private val localData: TvShowDataSource,
    private val remoteData: RemoteDataSource
) : IMainRepository<TvShow, DetailTvShow> {

    override fun getResult(supportSQLiteQuery: SupportSQLiteQuery?) =
        object : NetworkBoundResource<PagedList<TvShow>, TvShowResponse>(executors) {
            override fun loadFromDB(): LiveData<PagedList<TvShow>> {
                val result = localData.getResultRawQuery(supportSQLiteQuery)
                val convert = result?.mapByPage { DataMapper.listTvShowWithGenreToTvShows(it) }
                return convert?.let {
                    LivePagedListBuilder(it, Utils.config()).build()
                } ?: MutableLiveData()
            }
            override fun shouldFetch(data: PagedList<TvShow>?) = data == null || data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> = remoteData.getTvShows()
            override fun saveCallResult(data: TvShowResponse) {
                localData.insertResponse(data)
            }
        }.asLiveData()

    override fun getDetail(id: Int) =
        object : NetworkBoundResource<DetailTvShow, DetailTvShowResponse>(executors) {
            override fun loadFromDB(): LiveData<DetailTvShow> =
                Transformations.map(localData.getDetail(id)) { DataMapper.detailTvShowWithGenreToDetailTvShow(it) }
            override fun shouldFetch(data: DetailTvShow?) = data == null
            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> = remoteData.getTvShowById(id)
            override fun saveCallResult(data: DetailTvShowResponse) = localData.insertDetailResponse(data)
        }.asLiveData()

    override fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?) =
        object : NetworkBoundResource<PagedList<TvShow>, TvShowResponse>(executors) {
            override fun loadFromDB(): LiveData<PagedList<TvShow>> {
                val result = localData.getFavorite(supportSQLiteQuery)
                val convert = result?.mapByPage { DataMapper.listTvShowWithGenreToTvShows(it) }
                return convert?.let {
                    LivePagedListBuilder(it, Utils.config()).build()
                } ?: MutableLiveData()
            }
            override fun shouldFetch(data: PagedList<TvShow>?) = false
            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> = remoteData.getTvShows()
            override fun saveCallResult(data: TvShowResponse) {}
        }.asLiveData()

    override fun setFavorite(id: Int, isFavorite: Boolean) {
        executors.diskIO().execute {
            localData.updateFavorite(id, isFavorite)
        }
    }
}