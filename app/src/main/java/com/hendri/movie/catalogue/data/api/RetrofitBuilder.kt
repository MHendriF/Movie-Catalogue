package com.hendri.movie.catalogue.data.api

import com.hendri.movie.catalogue.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val httpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(
            Interceptor { chain ->
                return@Interceptor chain.proceed(
                    chain.request()
                        .newBuilder()
                        .build()
                )
            }
        )
        this.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.TMDB_BASE_URL)
        .client(httpClient.build())
        .build()

    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}