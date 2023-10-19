package com.rick.and.morty.data.base

import android.util.Log
import com.rick.and.morty.domain.base.FailureError
import com.rick.and.morty.domain.base.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

interface SafeApiCall {

    suspend fun safeUnitApiCall(apiCall: suspend () -> Response<Unit>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall.invoke()
                if (result.isSuccessful) {
                    Resource.Success(Unit)
                } else handleHttpException(result.code())
            } catch (throwable: Throwable) {
                handle(throwable)
            }
        }
    }

    suspend fun <A, D> safeApiCall(mapper: Mapper<A, D>, apiCall: suspend () -> A): Resource<D> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall.invoke()
                val model = mapper.map(result)
                Resource.Success(model)
            } catch (throwable: Throwable) {
                handle(throwable)
            }
        }
    }

    suspend fun <A, D> safeApiListCall(mapper: Mapper<A, D>, apiCall: suspend () -> List<A>): Resource<List<D>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall.invoke()
                val modelList = ListMapper(mapper).map(result)
                Resource.Success(modelList)
            } catch (throwable: Throwable) {
                Log.e("SafeApiCall error:", throwable.toString())
                handle(throwable)
            }
        }
    }

    private fun handle(throwable: Throwable): Resource.Failure {
        return when (throwable) {
            is HttpException -> {
                val errorResponse = ResponseErrorMapper.map(throwable)
                handleHttpException(throwable.code(), errorResponse?.error?.message)
            }
            is UnknownHostException, is IOException -> Resource.Failure(FailureError.Network)
            else -> Resource.Failure(FailureError.Generic)
        }
    }

    private fun handleHttpException(code: Int, message: String? = ""): Resource.Failure {
        return when(code) {
            HttpCodes.UNAUTHORIZED -> Resource.Failure(FailureError.Generic)
            HttpCodes.NOT_FOUND -> Resource.Failure(FailureError.Generic)
            else -> Resource.Failure(FailureError.Generic, message)
        }
    }
}