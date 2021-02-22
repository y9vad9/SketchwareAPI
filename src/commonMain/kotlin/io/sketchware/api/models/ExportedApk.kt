package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExportedApk(
    val alias: String,
    @SerialName("pkg_name")
    val packageName: String,
    @SerialName("url_id")
    val urlId: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("file_na,e")
    val fileName: String,
    @SerialName("reg_dt")
    val regDate: String,
    @SerialName("expire_dt")
    val expireDate: String
)
