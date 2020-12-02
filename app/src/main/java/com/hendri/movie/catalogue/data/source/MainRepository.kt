package com.hendri.movie.catalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hendri.movie.catalogue.data.source.local.LocalDataSource
import com.hendri.movie.catalogue.data.source.local.entity.Favorite
import com.hendri.movie.catalogue.data.source.local.entity.Movie
import com.hendri.movie.catalogue.data.source.local.entity.TvShow
import com.hendri.movie.catalogue.data.source.remote.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.RemoteDataSource
import com.hendri.movie.catalogue.data.source.remote.response.MovieResponse
import com.hendri.movie.catalogue.data.source.remote.response.MoviesResponse
import com.hendri.movie.catalogue.data.source.remote.vo.Resource
import com.hendri.movie.catalogue.utils.AppExecutors

class MainRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MainDataSource {

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ) : MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetWorkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> = localDataSource.getMovies()
            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getMovies()
            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<Movie>()
                for (item in data) {
                    val movie = Movie(
                        item.id,
                        item.overview,
                        item.originalLanguage,
                        item.originalTitle,
                        item.title,
                        item.posterPath,
                        item.backdropPath,
                        item.releaseDate,
                        item.popularity,
                        item.voteAverage,
                        item.voteCount,
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShow>>> {
        TODO("Not yet implemented")
    }

    override fun getMovieById(id: Int): LiveData<Resource<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getTvShowById(id: Int): LiveData<Resource<TvShow>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteByType(type: String): LiveData<PagedList<Favorite>> = LivePagedListBuilder(localDataSource.getFavoriteByType(type), 20).build()
    override fun insertFavorite(item: Favorite) = localDataSource.insertFavorite(item)
    override fun deleteFavorite(item: Favorite) = localDataSource.deleteFavorite(item)
    override fun getFavoriteById(id: Int): LiveData<Favorite> = localDataSource.getFavoriteById(id)


}