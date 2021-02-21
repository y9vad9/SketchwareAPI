package io.sketchware.api.models

import io.sketchware.api.serializers.StringLongSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedProject(
    val alias: String,
    @SerialName("pkg_name")
    val packageName: String,
    @SerialName("url_id")
    val urlId: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("app_name")
    val appName: String,
    @SerialName("reg_dt")
    @Serializable(StringLongSerializer::class)
    val registerDate: Long,
    @SerialName("expire_dt")
    @Serializable(StringLongSerializer::class)
    val expireDate: Long
)
