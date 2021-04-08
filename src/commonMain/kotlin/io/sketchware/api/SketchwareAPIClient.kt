package io.sketchware.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import io.sketchware.api.api.ProjectsAPI
import io.sketchware.api.api.SharedCollectionsAPI
import io.sketchware.api.api.UserAPI
import io.sketchware.api.models.*
import io.sketchware.api.utils.getRequest
import io.sketchware.api.utils.postRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * Sketchware API Client.
 * @param baseUrl - base url to Sketchware API.
 * @param loggerEnabled - set logger enabled/disabled
 */
class SketchwareAPIClient(
    private val baseUrl: String = "http://sketchware.io",
    loggerEnabled: Boolean = false
) {
    private val httpClient = HttpClient(CIO) {
        if (loggerEnabled)
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }
        addDefaultResponseValidation()
    }

    /**
     * The minimum working version to check the version of the application.
     * @return [Int] with version.
     */
    suspend fun getMinSketchwareVersion() =
        httpClient.getRequest<Int>("$baseUrl/min_sketchware_version.txt")

    /**
     * Sketchware projects API.
     */
    val projectsAPI by lazy { ProjectsAPI(httpClient, baseUrl) }

    /**
     * Sketchware user API.
     * Only for changing or getting user data
     * (API for modifying / sending / deleting user content, this API section is not responsible)
     */
    val userAPI by lazy { UserAPI(httpClient, baseUrl) }

    /**
     * Sketchware shared collections API (Views, Moreblocks, Blocks).
     * Also, there is API connected with [userAPI].
     */
    val sharedCollectionsAPI by lazy { SharedCollectionsAPI(httpClient, baseUrl) }

}