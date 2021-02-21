package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedTag(
    @SerialName("tag_text")
    val tagText: String,
    @SerialName("tag_id")
    val tagId: Int
)