package io.sketchware.api.api

import io.ktor.client.*
import io.sketchware.api.models.AuthorizedModel
import io.sketchware.api.models.ExportedApk
import io.sketchware.api.models.SharedProject
import io.sketchware.api.utils.postRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class UserAPI internal constructor(private val httpClient: HttpClient, private val baseUrl: String) {


    /**
     * Gets user's exported apks.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId])
     * @param email - user email.
     */
    suspend fun getUserExportedApks(sessionId: String, email: String) =
        httpClient.postRequest<List<ExportedApk>>("$baseUrl/reqListExportApk.do") {
            body = mapOf(
                "session_id" to sessionId,
                "login_id" to email
            )
        }

    /**
     * Gets user's exported projects.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId])
     * @param email - user email.
     */
    suspend fun getUserExportedProjects(sessionId: String, email: String) =
        httpClient.postRequest<List<ExportedApk>>("$baseUrl/reqListExportData.do") {
            body = mapOf(
                "session_id" to sessionId,
                "login_id" to email
            )
        }

    /**
     * Removes user's exported apk.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId])
     * @param email - user email.
     * @param urlId - url id (see: [ExportedApk.urlId])
     */
    suspend fun removeExportedApk(sessionId: String, email: String, urlId: Int) =
        httpClient.postRequest<String>("$baseUrl/reqDeleteApkUrl.do") {
            body = buildJsonObject {
                put("session_id", sessionId)
                put("login_id", email)
                put("url_id", urlId)
            }
        }

    /**
     * Removes user's exported project.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId])
     * @param email - user email.
     * @param urlId - url id (see: [SharedProject.urlId])
     */
    suspend fun removeExportedProject(sessionId: String, email: String, urlId: Int) =
        httpClient.postRequest<String>("$baseUrl/reqDeleteDataUrl.do") {
            body = buildJsonObject {
                put("session_id", sessionId)
                put("login_id", email)
                put("url_id", urlId)
            }
        }

    /**
     * User authorization.
     * @param email - email (example: vadimkotlinov@gmail.com).
     * @param deviceId - device id (can be empty).
     * @param accessToken - google access token.
     * @see (https://stackoverflow.com/questions/25526042/what-is-gcm-registration-id)
     */
    suspend fun authorizeUser(
        email: String,
        deviceId: String,
        accessToken: String,
        gcmId: String
    ) = httpClient.postRequest<AuthorizedModel>("$baseUrl/registerSnsUser.do") {
        body = buildJsonObject {
            put("is_sns_user", "Y")
            put("login_id", email)
            put("sns_kind", "google")
            put("device_id", deviceId)
            put("access_token", accessToken)
            put("gcm_id", gcmId)
        }
    }
}