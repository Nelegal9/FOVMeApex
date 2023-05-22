package com.alekhin.fovmeapex.authentication

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<out T>(val result: T) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}