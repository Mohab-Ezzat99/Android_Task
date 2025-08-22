package com.app.task.util

sealed class Resource<out T> {
    object Default : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Success<out T>(val value: T) : Resource<T>()
    class Failure(val message: String? = null,val messageId: Int? = null) : Resource<Nothing>()
}
