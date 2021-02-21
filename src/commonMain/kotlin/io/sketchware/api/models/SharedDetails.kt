package io.sketchware.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedDetails(
    @SerialName("long_desc")
    val longDescription: String,

    @SerialName("update_dt")
    val updateDate: String,

    @SerialName("shared_id")
    val sharedID: Long,

    @SerialName("user_alias")
    val userAlias: String,

    @SerialName("short_desc")
    val shortDescription: String,

    val recommend: Long,

    @SerialName("reg_dt")
    val regDate: String,

    @SerialName("down_cnt")
    val downloadCount: Long,

    @SerialName("file_seq")
    val fileSeq: String,

    @SerialName("comment_cnt")
    val commentCount: Long,

    @SerialName("like_cnt")
    val likeCount: Long,

    @SerialName("images_seq")
    val imagesSeq: String,

    @SerialName("uploader_id")
    val uploaderID: Long,

    @SerialName("block_name")
    val blockName: String? = null // not null if request about block / moreblock
)