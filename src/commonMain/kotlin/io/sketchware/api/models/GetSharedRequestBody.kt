package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSharedRequestBody(
    @SerialName("row_unit")
    val rowUnit: Int,
    @SerialName("row_start")
    val rowStart: Int,
    @SerialName("tag_text")
    val tag: String? = null // uses only in searchByTag request
)
