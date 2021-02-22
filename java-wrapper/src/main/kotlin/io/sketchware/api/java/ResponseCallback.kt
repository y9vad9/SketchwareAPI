package io.sketchware.api.java

import io.sketchware.api.models.Response

interface ResponseCallback<T> {
    fun onSuccess(body: T)
    fun onError(throwable: Throwable)
}