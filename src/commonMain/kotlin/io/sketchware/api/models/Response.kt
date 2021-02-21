package io.sketchware.api.models

data class Response<T>(
    val body: T? = null,
    val error: Throwable? = null
) {
    inline fun success(listener: (body: T) -> Unit): Response<T> = this.apply {
        body?.let(listener)
    }

    inline fun error(listener: (throwable: Throwable) -> Unit) = this.apply {
        error?.let(listener)
    }
}