package com.hendri.movie.catalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SupportSQLiteQuery

interface IMainDataSource <DataResponse, DataDetailResponse, DataResponseWithResult, DataResultWithGenre, DataDetail> {
    fun getResponse(): LiveData<DataResponseWithResult>
    fun getResult(): DataSource.Factory<Int, DataResultWithGenre>
    fun getResultRawQuery(supportSQLiteQuery: SupportSQLiteQuery?): DataSource.Factory<Int, DataResultWithGenre>?
    fun getDetail(id: Int): LiveData<DataDetail>
    fun insertResponse(data: DataResponse)
    fun insertDetailResponse(data: DataDetailResponse)
    fun updateFavorite(id: Int, isFavorite: Boolean)
    fun getFavorite(supportSQLiteQuery: SupportSQLiteQuery?): DataSource.Factory<Int, DataResultWithGenre>?
}