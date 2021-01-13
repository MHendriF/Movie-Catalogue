package com.hendri.movie.catalogue.data.source.remote.response

import android.os.Parcelable
import com.hendri.movie.catalogue.data.model.TvShow
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<TvShow>
) : Parcelable