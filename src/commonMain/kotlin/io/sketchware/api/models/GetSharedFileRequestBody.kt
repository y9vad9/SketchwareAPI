package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSharedFileRequestBody(
    @SerialName("shared_id")
    val sharedId: Int,
    @SerialName("file_name")
    val fileName: String
)