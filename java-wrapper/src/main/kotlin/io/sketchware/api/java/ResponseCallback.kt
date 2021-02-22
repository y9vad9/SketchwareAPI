package io.sketchware.api.java

interface ResponseCallback<T> {
    fun onSuccess(body: T)
    fun onError(throwable: Throwable)
}