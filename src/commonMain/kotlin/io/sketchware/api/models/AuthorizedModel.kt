package io.sketchware.api.models

import io.sketchware.api.serializers.SnsSerializer
import io.sketchware.api.serializers.StringLongSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizedModel(
    @SerialName("sns_kind")
    val snsKind: String,
    @SerialName("login_id")
    val loginId: String,
    @SerialName("gcm_id")
    val gcmId: String,
    @SerialName("session_id")
    val sessionId: String,
    val level: Int,
    val alias: String,
    @Serializable(StringLongSerializer::class)
    @SerialName("user_reg_dt")
    val userRegisterDate: Long,
    @Serializable(SnsSerializer::class)
    @SerialName("is_sns_user")
    val isSnsUser: Boolean,
    @SerialName("device_id")
    val deviceId: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("grade_level")
    val gradeLevel: Int,
    @SerialName("grade_point")
    val gradePoint: Int
)
