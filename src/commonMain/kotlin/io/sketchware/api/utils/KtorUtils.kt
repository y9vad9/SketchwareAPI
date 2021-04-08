package io.sketchware.api.utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.sketchware.api.models.Response

internal suspend inline fun <reified T> HttpClient.postRequest(
    url: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {},
) = try {
    Response(post<T>(urlString = url, block))
} catch (e: Exception) {
    Response<T>(null, e)
}

internal suspend inline fun <reified T> HttpClient.getRequest(
    url: String,
    crossinline block: HttpRequestBuilder.() -> Unit = {},
) = try {
    Response(get<T>(urlString = url, block))
} catch (e: Exception) {
    Response<T>(null, e)
}