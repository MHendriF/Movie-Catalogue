package com.hendri.movie.catalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hendri.movie.catalogue.data.source.remote.ApiResponse
import com.hendri.movie.catalogue.data.source.remote.StatusResponse
import com.hendri.movie.catalogue.data.source.remote.vo.Resource
import com.hendri.movie.catalogue.utils.AppExecutors

abstract class NetWorkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis") val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {
                StatusResponse.SUCCESS -> executors.diskIO().execute {
                    response.body?.let { saveCallResult(it) }
                    executors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.success(newData)
                        }
                    }
                }

                StatusResponse.EMPTY -> executors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(newData, response.message)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

}