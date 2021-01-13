package com.hendri.movie.catalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hendri.movie.catalogue.data.source.remote.network.ApiResponse
import com.hendri.movie.catalogue.utils.Executors
import com.hendri.movie.catalogue.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: Executors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.postValue(Resource.Loading(null))

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.postValue(Resource.Success(newData))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.postValue(Resource.Loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success ->
                    executors.diskIO().execute {
                        saveCallResult(response.data)
                        executors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.postValue(Resource.Success(newData))
                            }
                        }
                    }
                is ApiResponse.Empty -> executors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.postValue(Resource.Empty(newData))
                    }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.postValue(Resource.Error(response.message, newData))
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}