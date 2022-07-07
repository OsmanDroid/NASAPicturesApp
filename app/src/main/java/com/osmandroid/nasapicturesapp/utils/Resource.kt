package com.osmandroid.nasapicturesapp.utils

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()

    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean? = false,
        val errorCode: Int? = null,
        val errorBody: Any?
    ) : Resource<Nothing>()

}