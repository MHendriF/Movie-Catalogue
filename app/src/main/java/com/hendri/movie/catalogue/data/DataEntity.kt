package com.hendri.movie.catalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataEntity (
    val id: String,
    val title: String,
    val description: String,
    val genre: String,
    val releaseDate: String,
    val imgPoster: Int,
    val imgBackground: Int,
    val score: String
) : Parcelable