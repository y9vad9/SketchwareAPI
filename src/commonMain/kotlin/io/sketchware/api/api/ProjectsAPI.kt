package io.sketchware.api.api

import io.ktor.client.*
import io.sketchware.api.models.SharedProject
import io.sketchware.api.utils.postRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class ProjectsAPI internal constructor(private val httpClient: HttpClient, private val baseUrl: String) {

    /**
     * Gets exported project by [id].
     * @param id - url id (from [SharedProject.urlId].
     */
    suspend fun getExportedProject(id: Int) =
        httpClient.postRequest<List<SharedProject>>(url = "$baseUrl/reqGetExportData.do") {
            body = mapOf("url_id" to id)
        }

    /**
     * Gets exported project file.
     * @param fileName - File name (actually can be: project,
     * data.zip, res_font.zip, res_sound.zip, res_images.zip).
     * @param userId - project exporter id.
     * @param urlId - url id (from [SharedProject.urlId].
     */
    suspend fun getProjectExportedFile(fileName: String, userId: Int, urlId: Int) =
        httpClient.postRequest<ByteArray>(url = "$baseUrl/getExportProjectFile.do") {
            body = buildJsonObject {
                put("file_name", fileName)
                put("user_id", userId)
                put("url_id", urlId)
            }
        }
}