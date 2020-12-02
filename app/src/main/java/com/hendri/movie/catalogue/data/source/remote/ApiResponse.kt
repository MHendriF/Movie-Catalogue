package com.hendri.movie.catalogue.data.source.remote

import com.hendri.movie.catalogue.data.source.remote.vo.Status

class ApiResponse<out T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(status = StatusResponse.SUCCESS, body = body, message = null)

        fun <T> error(message: String, body: T?): ApiResponse<T> = ApiResponse(status = StatusResponse.ERROR, body = body, message = message)
    }
}