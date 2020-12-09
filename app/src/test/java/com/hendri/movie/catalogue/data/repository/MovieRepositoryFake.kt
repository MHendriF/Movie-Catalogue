package com.hendri.movie.catalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SupportSQLiteQuery
import com.hendri.movie.catalogue.data.NetworkBoundResource
import com.hendri.movie.catalogue.vo.Resource
import com.hendri.movie.catalogue.data.model.DetailMovie
import com.hendri.movie.catalogue.data.model.Movie
import com.hendri.movie.catalogue.data.source.local.MovieDataSource
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.response.DetailMovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.utils.DataMapper
import com.hendri.movie.catalogue.utils.Executors

class MovieRepositoryFake(
    private val executors: Executors,
    private val localData: MovieDataSource,
    private val remoteData: RemoteDataSource
) : IMainRepository<Movie, DetailMovie> {

    override fun getResult(supportSQLiteQuery: SupportSQLiteQuery?): LiveData<Resource<PagedList<Movie>>> {
        return object :
            NetworkBoundResource<PagedList<Movie>, MovieResponse>(executors) {
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                val result = localData.getResultRawQuery(supportSQLiteQuery)
                val convert = result?.mapByPage { DataMapper.listMovieWithGenreToMovies(it) }
                return convert?.let {
                    LivePagedListBuilder(it, Utils.config()).build()
                } ?: MutableLiveData()
            }
            override fun shouldFetch(data: PagedList<Movie>?) = data == null || data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteData.getMovies()
            override fun saveCallResult(data: MovieResponse) {
                localData.insertResponse(data)
            }
        }.asLiveData()
    }

    override fun getDetail(id: Int): LiveData<Resource<DetailMovie>> {
        return object : NetworkBoundResource<DetailMovie, DetailMovieResponse>(executors) {
            override fun loadFromDB(): LiveData<DetailMovie> =
                Transformations.map(localData.getDetail(id)) {
                    DataMapper.detailMovieWithGenreToDetailMovie(
                        it
                    )
                }
            override fun shouldFetch(data: DetailMovie?): Boolean = (data == null)
            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> = remoteData.getMovieById(id)
            override fun saveCallResult(data: DetailMovieResponse) = localData.insertDetailResponse(data)
        }.asLiveData()
    }

    override fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?) =
        object : NetworkBoundResource<PagedList<Movie>, MovieResponse>(executors) {
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                val result = localData.getFavorite(supportSQLiteQuery)
                val convert = result?.mapByPage { DataMapper.listMovieWithGenreToMovies(it) }
                return convert?.let {
                    LivePagedListBuilder(it, Utils.config()).build()
                } ?: MutableLiveData()
            }
            override fun shouldFetch(data: PagedList<Movie>?) = false
            override fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteData.getMovies()
            override fun saveCallResult(data: MovieResponse) {}
        }.asLiveData()

    override fun setFavorite(id: Int, isFavorite: Boolean) {
        executors.diskIO().execute { localData.updateFavorite(id, isFavorite) }
    }
}