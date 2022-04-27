package com.instant.instantnews.utils.resource

sealed class Resource<out T> {
    data class Success<T>(val value: T) : Resource<T>()

    object Loading : Resource<Nothing>()

    data class Error(
        val cause: String
    ) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$value]"
            is Error -> "Error[exception=$cause]"
            else -> "Loading"
        }
    }

}