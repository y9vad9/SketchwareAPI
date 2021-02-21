package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseShared(
    @SerialName("long_desc")
    val longDescription: String,
    @SerialName("update_dt")
    val updateDate: String,
    @SerialName("shared_id")
    val sharedId: Int,
    @SerialName("user_alias")
    val userAlias: String,
    @SerialName("short_desc")
    val shortDescription: String,
    val recommend: Int,
    @SerialName("reg_dt")
    val registerDate: String,
    @SerialName("view_name")
    val viewName: String? = null,
    @SerialName("down_cnt")
    val downloadCount: Int,
    @SerialName("file_seq")
    val fileSequence: String,
    @SerialName("like_cnt")
    val likeCount: Int,
    @SerialName("comment_cnt")
    val commentCount: Int,
    @SerialName("images_seq")
    val imagesSequence: String,
    @SerialName("uploader_id")
    val uploaderId: Int,
    @SerialName("block_name")
    val blockName: String? = null
)
