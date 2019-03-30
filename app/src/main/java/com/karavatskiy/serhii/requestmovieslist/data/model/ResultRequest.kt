package com.karavatskiy.serhii.requestmovieslist.data.model

sealed class ResultRequest<out T : Any>
class Success<out T : Any>(val data: T) : ResultRequest<T>()
class Error(val exception: Throwable, val message: String = exception.localizedMessage) : ResultRequest<Nothing>()