package com.hendri.movie.catalogue.data.repository

import com.hendri.movie.catalogue.data.api.ApiHelper
import com.hendri.movie.catalogue.data.local.DatabaseHelper
import com.hendri.movie.catalogue.data.response.TvShow
import com.hendri.movie.catalogue.data.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class TvShowRepository(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
) {

    suspend fun getTvShowsFromApi(): Response<TvShowResponse> {
        Timber.d("Trace :: getTvShowsFromApi")
        return withContext(Dispatchers.IO) {
            apiHelper.getTvShows()
        }
    }

    suspend fun getTvShowsFromDB(): List<TvShow> {
        return dbHelper.getTvShows()
    }
}