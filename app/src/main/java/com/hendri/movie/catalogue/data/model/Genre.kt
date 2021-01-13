package com.hendri.movie.catalogue.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val genre_code: Int,
    val name: String
) : Parcelable