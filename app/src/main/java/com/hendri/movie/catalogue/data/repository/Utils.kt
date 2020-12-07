package com.hendri.movie.catalogue.data.repository

import androidx.paging.PagedList

object Utils {
    fun config(pageSize: Int = 5): PagedList.Config =
        PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(pageSize).build()
}