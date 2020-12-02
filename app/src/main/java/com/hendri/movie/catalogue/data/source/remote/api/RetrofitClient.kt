package com.hendri.movie.catalogue.data.source.remote.api

import com.hendri.movie.catalogue.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun generateRetrofitClient(): RetrofitService {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.TMDB_BASE_URL)
            .build().create(RetrofitService::class.java)
    }
}