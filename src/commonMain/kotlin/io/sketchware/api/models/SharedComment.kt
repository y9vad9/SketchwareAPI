package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedComment(
    @SerialName("comment_id")
    val commentId: Int,
    val level: Int,
    @SerialName("user_alias")
    val userAlias: String,
    @SerialName("shared_id")
    val sharedId: Int,
    @SerialName("user_id")
    val userId: Int,
    val comment: String,
    @SerialName("reg_dt")
    val regDate: String
)
