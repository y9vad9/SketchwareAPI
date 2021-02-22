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
        httpClient.get<Int>("$baseUrl/min_sketchware_version.txt")

    /**
     * Gets the last loaded Shared Views.
     * @param itemsToLoad - how many views should be loaded per request.
     * @param index - the index from which to load views.
     */
    suspend fun getRecentSharedViews(itemsToLoad: Int, index: Int) =
        getShared("Views", "Recent", GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the last loaded Shared Moreblocks.
     * @param itemsToLoad - how many moreblocks should be loaded per request.
     * @param index - the index from which to load moreblocks.
     */
    suspend fun getRecentSharedMoreblocks(itemsToLoad: Int, index: Int) =
        getShared("MoreBlocks", "Recent", GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the last loaded Shared Blocks.
     * @param itemsToLoad - how many blocks should be loaded per request.
     * @param index - the index from which to load blocks.
     */
    suspend fun getRecentSharedBlocks(itemsToLoad: Int, index: Int) =
        getShared("Blocks", "Recent", GetSharedRequestBody(itemsToLoad, index))

    /**
     * Private API with common model.
     * @param tag - Shared Name (Views/MoreBlocks/Blocks)
     * @param tag2 - Sort by recent(Recent) / most liked(LikeCnt) / most downloaded(downCnt)
     */
    private suspend fun getShared(tag: String, tag2: String, requestBody: GetSharedRequestBody) =
        httpClient.postRequest<List<BaseShared>>(url = "$baseUrl/req${tag2}Shared${tag}.do") {
            body = requestBody
        }

    /**
     * Gets the most liked Shared Views.
     * @param itemsToLoad - how many views should be loaded per request.
     * @param index - the index from which to load views.
     */
    suspend fun getMostLikedSharedViews(itemsToLoad: Int, index: Int) =
        getMostLikedSharedViews(GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the most liked Shared Views.
     * @param requestBody - request data.
     */
    suspend fun getMostLikedSharedViews(requestBody: GetSharedRequestBody) =
        getShared("Views", "LikeCnt", requestBody)

    /**
     * Gets the most liked Shared Moreblocks.
     * @param requestBody - request data.
     */
    suspend fun getMostLikedSharedMoreblocks(requestBody: GetSharedRequestBody) =
        getShared("MoreBlocks", "LikeCnt", requestBody)

    /**
     * Gets the most liked Shared Moreblocks.
     * @param itemsToLoad - how many views should be loaded per request.
     * @param index - the index from which to load views.
     */
    suspend fun getMostLikedSharedMoreblocks(itemsToLoad: Int, index: Int) =
        getMostLikedSharedMoreblocks(GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the most liked Shared Blocks.
     * @param itemsToLoad - how many views should be loaded per request.
     * @param index - the index from which to load views.
     */
    suspend fun getMostLikedSharedBlocks(itemsToLoad: Int, index: Int) =
        getShared("Blocks", "LikeCnt", GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the most liked Shared Blocks.
     * @param requestBody - request data.
     */
    suspend fun getMostLikedSharedBlocks(requestBody: GetSharedRequestBody) =
        getShared("MoreBlocks", "LikeCnt", requestBody)

    /**
     * Gets the most downloaded Shared Views.
     * @param itemsToLoad - how many views should be loaded per request.
     * @param index - the index from which to load views.
     */
    suspend fun getMostDownloadedSharedViews(itemsToLoad: Int, index: Int) =
        getMostDownloadedSharedViews(GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the most downloaded Shared Moreblocks.
     * @param itemsToLoad - how many moreblocks should be loaded per request.
     * @param index - the index from which to load moreblocks.
     */
    suspend fun getMostDownloadedSharedMoreblocks(itemsToLoad: Int, index: Int) =
        getMostDownloadedSharedMoreblocks(GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the most downloaded Shared Blocks.
     * @param itemsToLoad - how many blocks should be loaded per request.
     * @param index - the index from which to load blocks.
     */
    suspend fun getMostDownloadedSharedBlocks(itemsToLoad: Int, index: Int) =
        getMostDownloadedShared("Blocks", GetSharedRequestBody(itemsToLoad, index))

    /**
     * Gets the most downloaded Shared Views.
     * @param requestBody - request data.
     */
    suspend fun getMostDownloadedSharedViews(requestBody: GetSharedRequestBody) =
        getMostDownloadedShared("Views", requestBody)

    /**
     * Gets the most downloaded Shared Moreblocks.
     * @param requestBody - request data.
     */
    suspend fun getMostDownloadedSharedMoreblocks(requestBody: GetSharedRequestBody) =
        getMostDownloadedShared("MoreBlocks", requestBody)

    /**
     * Gets the most downloaded Shared Blocks.
     * @param requestBody - request data.
     */
    suspend fun getMostDownloadedSharedBlocks(requestBody: GetSharedRequestBody) =
        this.getMostDownloadedShared("Blocks", requestBody)

    /**
     * Private common API for most downloaded.
     * @param tag - Views/MoreBlocks/Blocks
     * @param requestBody - request data.
     */
    private suspend fun getMostDownloadedShared(tag: String, requestBody: GetSharedRequestBody) =
        getShared(tag, "downCnt", requestBody)

    /**
     * Gets shared view file.
     * @param sharedId - Shared Id (from [BaseShared.sharedId])
     * @param fileName - Shared View File to download.
     */
    suspend fun getSharedViewFile(sharedId: Int, fileName: String) =
        getSharedViewFile(GetSharedFileRequestBody(sharedId, fileName))

    /**
     * Gets shared view file.
     * @param requestBody - request data.
     */
    suspend fun getSharedViewFile(requestBody: GetSharedFileRequestBody) =
        getSharedFileCommon("View", requestBody)

    /**
     * Private common API to get file of some shared thing.
     * @param tag - View/MoreBlock/Block.
     * @param requestBody - request data.
     */
    private suspend fun getSharedFileCommon(tag: String, requestBody: GetSharedFileRequestBody) =
        httpClient.post<ByteArray>("$baseUrl/downloadShared${tag}File.do") {
            body = requestBody
        }

    /**
     * Private common API to get shared details.
     * @param id - shared id (from [BaseShared.sharedId].
     * @param tag - View/MoreBlock/Block.
     */
    private suspend fun getSharedDetails(id: Int, tag: String) =
        httpClient.postRequest<SharedDetails>(url = "$baseUrl/reqShared${tag}Detail.do") {
            body = mapOf("shared_id" to id)
        }

    /**
     * Private common API to get comments of some shared thing.
     * @param id - shared id (from [BaseShared.sharedId].
     * @param tag - View/MoreBlock/Block.
     */
    private suspend fun getSharedComments(id: Int, tag: String) =
        httpClient.postRequest<SharedComment>(url = "$baseUrl/reqShared${tag}CommentsList.do") {
            body = mapOf("shared_id" to id)
        }

    /**
     * Private common API to get tags of some shared thing.
     * @param id - shared id (from [BaseShared.sharedId].
     * @param tag - View/MoreBlock/Block.
     */
    private suspend fun getSharedTags(id: Int, tag: String) =
        httpClient.postRequest<SharedTag>(url = "$baseUrl/reqShared${tag}Tags.do") {
            body = mapOf("shared_id" to id)
        }

    /**
     * Gets shared view details.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedViewDetails(id: Int) = getSharedDetails(id, "View")

    /**
     * Gets shared moreblock details.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedMoreblockDetails(id: Int) = getSharedDetails(id, "MoreBlock")

    /**
     * Gets shared block details.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedBlockDetails(id: Int) = getSharedDetails(id, "Block")

    /**
     * Gets shared view comments.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedViewComments(id: Int) = getSharedComments(id, "View")

    /**
     * Gets shared moreblock comments.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedMoreblockComments(id: Int) = getSharedComments(id, "MoreBlock")

    /**
     * Gets shared block comments.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedBlockComments(id: Int) = getSharedComments(id, "Block")

    /**
     * Gets shared view tags.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedViewTags(id: Int) = getSharedTags(id, "View")

    /**
     * Gets shared moreblock tags.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedMoreblockTags(id: Int) = getSharedTags(id, "MoreBlock")

    /**
     * Gets shared block tags.
     * @param id - shared id (from [BaseShared.sharedId].
     */
    suspend fun getSharedBlockTags(id: Int) = getSharedTags(id, "Block")

    /**
     * Gets exported project by [id].
     * @param id - url id (from [SharedProject.urlId].
     */
    suspend fun getProject(id: Int) =
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

    /**
     * Private common api to add comments to shared.
     * @param tag - View/MoreBlock/Block
     * @param sessionId - Session id (see: [AuthorizedModel.sessionId], [authorizeUser]).
     * @param email - authorized user email.
     * @param comment - comment text value.
     * @see authorizeUser
     */
    private suspend fun addCommentToShared(
        tag: String, sessionId: String, email: String, sharedId: Int, comment: String
    ) = httpClient.postRequest<String>(url = "$baseUrl/reqInsertShared${tag}Comment.do") {
        body = buildJsonObject {
            put("session_id", sessionId)
            put("login_id", email)
            put("shared_id", sharedId)
            put("comment", comment)
        }
    }

    /**
     * Sends comment to shared view.
     * @param sessionId - Session id (see: [AuthorizedModel.sessionId], [authorizeUser]).
     * @param email - authorized user email.
     * @param comment - comment text value.
     * @see authorizeUser
     */
    suspend fun addCommentToSharedView(
        sessionId: String, email: String, sharedId: Int, comment: String
    ) = addCommentToShared("View", sessionId, email, sharedId, comment)

    /**
     * Sends comment to shared moreblock.
     * @param sessionId - Session id (see: [AuthorizedModel.sessionId], [authorizeUser]).
     * @param email - authorized user email.
     * @param comment - comment text value.
     * @see authorizeUser
     */
    suspend fun addCommentToSharedMoreblock(
        sessionId: String, email: String, sharedId: Int, comment: String
    ) = addCommentToShared("MoreBlock", sessionId, email, sharedId, comment)

    /**
     * Sends comment to shared block.
     * @param sessionId - Session id (see: [AuthorizedModel.sessionId], [authorizeUser]).
     * @param email - authorized user email.
     * @param comment - comment text value.
     * @see authorizeUser
     */
    suspend fun addCommentToSharedBlock(
        sessionId: String, email: String, sharedId: Int, comment: String
    ) = addCommentToShared("Block", sessionId, email, sharedId, comment)

    /**
     * Private common api to search shared thing by tag.
     * @param tag - Views/MoreBlocks/Blocks
     * @param requestBody - request body with required value 'tag'.
     */
    private suspend fun searchByTagShared(
        tag: String, requestBody: GetSharedRequestBody
    ) = httpClient.postRequest<List<BaseShared>>(url = "$baseUrl/reqTagSearchShared${tag}.do") {
        body = requestBody
    }

    /**
     * Searches views by tag.
     * @param itemsToLoad - how much views will be loaded.
     * @param index - from which position it should load.
     */
    suspend fun searchViewsByTag(
        itemsToLoad: Int, index: Int, tag: String
    ) = searchByTagShared("Views", GetSharedRequestBody(itemsToLoad, index, tag))

    /**
     * Searches moreblocks by tag.
     * @param itemsToLoad - how much views will be loaded.
     * @param index - from which position it should load.
     */
    suspend fun searchMoreblocksByTag(
        itemsToLoad: Int, index: Int, tag: String
    ) = searchByTagShared("MoreBlocks", GetSharedRequestBody(itemsToLoad, index, tag))

    /**
     * Searches blocks by tag.
     * @param itemsToLoad - how much views will be loaded.
     * @param index - from which position it should load.
     */
    suspend fun searchBlocksByTag(
        itemsToLoad: Int, index: Int, tag: String
    ) = searchByTagShared("Blocks", GetSharedRequestBody(itemsToLoad, index, tag))

    /**
     * Gets all tags that have ever been used.
     */
    suspend fun getAllTags() = httpClient.postRequest<List<SharedTag>>(url = "$baseUrl/reqAllTags.do") {
        body = emptyMap<Unit, Unit>()
    }

    /**
     * Private common API to remove shared comment.
     * @param tag - View/MoreBlock/Block
     * @param sessionId - session id (see: [AuthorizedModel.sessionId], [authorizeUser])
     * @param email - user email
     * @param sharedId - shared id (see: [BaseShared.sharedId])
     * @param commentId - comment id which should be removed.
     */
    private suspend fun removeSharedComment(
        tag: String, sessionId: String, email: String, sharedId: Int, commentId: Int
    ) = httpClient.postRequest<String>(url = "$baseUrl/reqDeleteShared${tag}Comment.do") {
        body = buildJsonObject {
            put("session_id", sessionId)
            put("login_id", email)
            put("shared_id", sharedId)
            put("comment_id", commentId)
        }
    }

    /**
     * Removes comment in shared view by id.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId], [authorizeUser])
     * @param email - user email
     * @param sharedId - shared id (see: [BaseShared.sharedId])
     * @param commentId - comment id which should be removed.
     */
    suspend fun removeSharedViewComment(
        sessionId: String, email: String, sharedId: Int, commentId: Int
    ) = removeSharedComment("View", sessionId, email, sharedId, commentId)


    /**
     * Removes comment in shared moreblock by id.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId], [authorizeUser])
     * @param email - user email
     * @param sharedId - shared id (see: [BaseShared.sharedId])
     * @param commentId - comment id which should be removed.
     */
    suspend fun removeSharedMoreblockComment(
        sessionId: String, email: String, sharedId: Int, commentId: Int
    ) = removeSharedComment("MoreBlock", sessionId, email, sharedId, commentId)

    /**
     * Removes comment in shared block by id.
     * @param sessionId - session id (see: [AuthorizedModel.sessionId], [authorizeUser])
     * @param email - user email
     * @param sharedId - shared id (see: [BaseShared.sharedId])
     * @param commentId - comment id which should be removed.
     */
    suspend fun removeSharedBlockComment(
        sessionId: String, email: String, sharedId: Int, commentId: Int
    ) = removeSharedComment("Block", sessionId, email, sharedId, commentId)

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
    ) = httpClient.postRequest<AuthorizedModel>("https://sketchware.io/registerSnsUser.do") {
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