package com.hendri.movie.catalogue.data.source.remote.response

import android.os.Parcelable
import com.hendri.movie.catalogue.data.source.remote.response.the_movie_db.MovieResult
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieResult>
) : Parcelable