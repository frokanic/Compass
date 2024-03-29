package com.projects.compass.utils

sealed class Resource<T>(
    val data: T? = null,
    val errorResId: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(errorResId: Int, data: T? = null) : Resource<T>(data, errorResId)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}