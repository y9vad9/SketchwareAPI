package io.sketchware.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.sketchware.api.models.*
import io.sketchware.api.utils.postRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

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

    suspend fun getMinSketchwareVersion() =
        httpClient.get<Int>("$baseUrl/min_sketchware_version.txt")

    suspend fun getRecentSharedViews(itemsToLoad: Int, itemStart: Int) =
        getShared("Views", "Recent", GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getRecentSharedMoreblocks(itemsToLoad: Int, itemStart: Int) =
        getShared("MoreBlocks", "Recent", GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getRecentSharedBlocks(itemsToLoad: Int, itemStart: Int) =
        getShared("Blocks", "Recent", GetSharedRequestBody(itemsToLoad, itemStart))

    private suspend fun getShared(tag: String, tag2: String, requestBody: GetSharedRequestBody) =
        httpClient.postRequest<List<BaseShared>>(url = "$baseUrl/req${tag2}Shared${tag}.do") {
            body = requestBody
        }

    suspend fun getMostLikedSharedViews(itemsToLoad: Int, itemStart: Int) =
        getMostLikedSharedViews(GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getMostLikedSharedViews(requestBody: GetSharedRequestBody) =
        getShared("Views", "LikeCnt", requestBody)

    suspend fun getMostLikedSharedMoreblocks(requestBody: GetSharedRequestBody) =
        getShared("MoreBlocks", "LikeCnt", requestBody)

    suspend fun getMostLikedSharedMoreblocks(itemsToLoad: Int, itemStart: Int) =
        getMostLikedSharedMoreblocks(GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getMostLikedSharedBlocks(itemsToLoad: Int, itemStart: Int) =
        getShared("Blocks", "LikeCnt", GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getMostLikedSharedBlocks(requestBody: GetSharedRequestBody) =
        getShared("MoreBlocks", "LikeCnt", requestBody)

    suspend fun getMostDownloadedSharedViews(itemsToLoad: Int, itemStart: Int) =
        getMostDownloadedSharedViews(GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getMostDownloadedSharedMoreblocks(itemsToLoad: Int, itemStart: Int) =
        getMostDownloadedSharedMoreblocks(GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getMostDownloadedSharedBlocks(itemsToLoad: Int, itemStart: Int) =
        getMostDownloadedShared("Blocks", GetSharedRequestBody(itemsToLoad, itemStart))

    suspend fun getMostDownloadedSharedViews(requestBody: GetSharedRequestBody) =
        getMostDownloadedShared("Views", requestBody)

    suspend fun getMostDownloadedSharedMoreblocks(requestBody: GetSharedRequestBody) =
        getMostDownloadedShared("MoreBlocks", requestBody)

    suspend fun getMostDownloadedSharedBlocks(requestBody: GetSharedRequestBody) =
        this.getMostDownloadedShared("Blocks", requestBody)

    suspend fun getMostDownloadedShared(tag: String, requestBody: GetSharedRequestBody) =
        httpClient.postRequest<List<BaseShared>>(url = "$baseUrl/reqDownCntShared${tag}.do") {
            body = requestBody
        }

    suspend fun getSharedViewFile(sharedId: Int, fileName: String) =
        getSharedViewFile(GetSharedFileRequestBody(sharedId, fileName))

    suspend fun getSharedViewFile(requestBody: GetSharedFileRequestBody) =
        getSharedFileCommon("View", requestBody)

    private suspend fun getSharedFileCommon(tag: String, requestBody: GetSharedFileRequestBody) =
        httpClient.post<ByteArray>("$baseUrl/downloadShared${tag}File.do") {
            body = requestBody
        }

    private suspend fun getSharedDetails(id: Int, tag: String) =
        httpClient.postRequest<SharedDetails>(url = "$baseUrl/reqShared${tag}Detail.do") {
            body = mapOf("shared_id" to id)
        }

    private suspend fun getSharedComments(id: Int, tag: String) =
        httpClient.postRequest<SharedDetails>(url = "$baseUrl/reqShared${tag}CommentsList.do") {
            body = mapOf("shared_id" to id)
        }

    private suspend fun getSharedTags(id: Int, tag: String) =
        httpClient.postRequest<SharedTag>(url = "$baseUrl/reqShared${tag}Tags.do") {
            body = mapOf("shared_id" to id)
        }

    suspend fun getSharedViewDetails(id: Int) = getSharedDetails(id, "View")
    suspend fun getSharedMoreblockDetails(id: Int) = getSharedDetails(id, "MoreBlock")
    suspend fun getSharedBlockDetails(id: Int) = getSharedDetails(id, "Block")

    suspend fun getSharedViewComments(id: Int) = getSharedComments(id, "View")
    suspend fun getSharedMoreblockComments(id: Int) = getSharedComments(id, "MoreBlock")
    suspend fun getSharedBlockComments(id: Int) = getSharedComments(id, "Block")

    suspend fun getSharedViewTags(id: Int) = getSharedTags(id, "View")
    suspend fun getSharedMoreblockTags(id: Int) = getSharedTags(id, "MoreBlock")
    suspend fun getSharedBlockTags(id: Int) = getSharedTags(id, "Block")

    suspend fun getProject(id: Int) =
        httpClient.postRequest<List<SharedProject>>(url = "$baseUrl/reqGetExportData.do") {
            body = mapOf("url_id" to id)
        }

    suspend fun getProjectExportedFile(fileName: String, userId: Int, urlId: Int) =
        httpClient.postRequest<ByteArray>(url = "$baseUrl/getExportProjectFile.do") {
            body = buildJsonObject {
                put("file_name", fileName)
                put("user_id", userId)
                put("url_id", urlId)
            }
        }

    private suspend fun addCommentToShared(
        tag: String, sessionId: String, loginId: String, sharedId: Int, comment: String
    ) = httpClient.postRequest<String>(url = "$baseUrl/reqInsertShared${tag}Comment.do") {
        body = buildJsonObject {
            put("session_id", sessionId)
            put("login_id", loginId)
            put("shared_id", sharedId)
            put("comment", comment)
        }
    }

    suspend fun addCommentToSharedView(
        sessionId: String, loginId: String, sharedId: Int, comment: String
    ) = addCommentToShared("View", sessionId, loginId, sharedId, comment)

    suspend fun addCommentToSharedMoreblock(
        sessionId: String, loginId: String, sharedId: Int, comment: String
    ) = addCommentToShared("MoreBlock", sessionId, loginId, sharedId, comment)

    suspend fun addCommentToSharedBlock(
        sessionId: String, loginId: String, sharedId: Int, comment: String
    ) = addCommentToShared("Block", sessionId, loginId, sharedId, comment)

    private suspend fun searchByTagShared(
        tag: String, requestBody: GetSharedRequestBody
    ) = httpClient.postRequest<List<BaseShared>>(url = "$baseUrl/reqTagSearchShared${tag}.do") {
        body = requestBody
    }

    suspend fun searchViewsByTag(
        itemsToLoad: Int, itemStart: Int, tag: String
    ) = searchByTagShared("Views", GetSharedRequestBody(itemsToLoad, itemStart, tag))

    suspend fun searchMoreblocksByTag(
        itemsToLoad: Int, itemStart: Int, tag: String
    ) = searchByTagShared("MoreBlocks", GetSharedRequestBody(itemsToLoad, itemStart, tag))

    suspend fun searchBlocksByTag(
        itemsToLoad: Int, itemStart: Int, tag: String
    ) = searchByTagShared("Blocks", GetSharedRequestBody(itemsToLoad, itemStart, tag))

    suspend fun getAllTags() = httpClient.postRequest<List<SharedTag>>(url = "$baseUrl/reqAllTags.do") {
        body = emptyMap<Unit, Unit>()
    }

    private suspend fun removeSharedComment(
        tag: String, sessionId: String, loginId: String, sharedId: Int, commentId: Int
    ) = httpClient.postRequest<String>(url = "$baseUrl/reqDeleteShared${tag}Comment.do") {
        body = buildJsonObject {
            put("session_id", sessionId)
            put("login_id", loginId)
            put("shared_id", sharedId)
            put("comment_id", commentId)
        }
    }

    suspend fun removeSharedViewComment(
        sessionId: String, loginId: String, sharedId: Int, commentId: Int
    ) = removeSharedComment("View", sessionId, loginId, sharedId, commentId)

    suspend fun removeSharedMoreblockComment(
        sessionId: String, loginId: String, sharedId: Int, commentId: Int
    ) = removeSharedComment("MoreBlock", sessionId, loginId, sharedId, commentId)

    suspend fun removeSharedBlockComment(
        sessionId: String, loginId: String, sharedId: Int, commentId: Int
    ) = removeSharedComment("Block", sessionId, loginId, sharedId, commentId)

}