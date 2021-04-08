package io.sketchware.api.api

import io.ktor.client.*
import io.ktor.http.*
import io.sketchware.api.models.*
import io.sketchware.api.utils.postRequest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SharedCollectionsAPI internal constructor(private val httpClient: HttpClient, private val baseUrl: String) {


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
        getShared(tag, "DownCnt", requestBody)

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
     * Gets shared block file.
     * @param sharedId - Shared Id (from [BaseShared.sharedId])
     * @param fileName - Shared Block File to download.
     */
    suspend fun getSharedBlockFile(sharedId: Int, fileName: String) =
        getSharedBlockFile(GetSharedFileRequestBody(sharedId, fileName))

    /**
     * Gets shared block file.
     * @param requestBody - request data.
     */
    suspend fun getSharedBlockFile(requestBody: GetSharedFileRequestBody) =
        getSharedFileCommon("Block", requestBody)

    /**
     * Gets shared view file.
     * @param sharedId - Shared Id (from [BaseShared.sharedId])
     * @param fileName - Shared Moreblock File to download.
     */
    suspend fun getSharedMoreblockFile(sharedId: Int, fileName: String) =
        getSharedMoreblockFile(GetSharedFileRequestBody(sharedId, fileName))

    /**
     * Gets shared view file.
     * @param requestBody - request data.
     */
    suspend fun getSharedMoreblockFile(requestBody: GetSharedFileRequestBody) =
        getSharedFileCommon("MoreBlock", requestBody)

    /**
     * Private common API to get file of some shared thing.
     * @param tag - View/MoreBlock/Block.
     * @param requestBody - request data.
     */
    private suspend fun getSharedFileCommon(tag: String, requestBody: GetSharedFileRequestBody) =
        httpClient.postRequest<ByteArray>("$baseUrl/downloadShared${tag}File.do") {
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
        httpClient.postRequest<List<SharedComment>>(url = "$baseUrl/reqShared${tag}CommentsList.do") {
            body = mapOf("shared_id" to id)
        }

    /**
     * Private common API to get tags of some shared thing.
     * @param id - shared id (from [BaseShared.sharedId].
     * @param tag - View/MoreBlock/Block.
     */
    private suspend fun getSharedTags(id: Int, tag: String) =
        httpClient.postRequest<List<SharedTag>>(url = "$baseUrl/reqShared${tag}Tags.do") {
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
     * Private common api to add download to shared collection.
     */
    private suspend fun addDownloadToShared(tag: String, sharedId: Int) =
        httpClient.postRequest<String>("$baseUrl/reqShared${tag}DownCntAdd.do") {
            body = buildJsonObject {
                put("shared_id", sharedId)
            }
        }

    /**
     * Adds 1 to download count for shared moreblock by [sharedId].
     * @param sharedId - Shared id (see: [BaseShared.sharedId]).
     */
    suspend fun addDownloadToSharedMoreblockCount(sharedId: Int) =
        addDownloadToShared("MoreBlock", sharedId)

    /**
     * Adds 1 to download count for shared block by [sharedId].
     * @param sharedId - Shared id (see: [BaseShared.sharedId]).
     */
    suspend fun addDownloadToSharedBlockCount(sharedId: Int) =
        addDownloadToShared("Block", sharedId)

    /**
     * Adds 1 to download count for shared view by [sharedId].
     * @param sharedId - Shared id (see: [BaseShared.sharedId]).
     */
    suspend fun addDownloadToSharedViewCount(sharedId: Int) =
        addDownloadToShared("View", sharedId)

    /**
     * Private common api for shared collections.
     * @return string with result.
     */
    private suspend fun likeSharedCollection(
        tag: String, sessionId: String, email: String, sharedId: Int
    ) = httpClient.postRequest<String>("$baseUrl/reqInsertShared${tag}Like.do") {
        body = buildJsonObject {
            put("session_id", sessionId)
            put("login_id", email)
            put("shared_id", sharedId)
        }
    }

    /**
     * Add like to shared view count.
     * @param sessionId - Session id (see [authorizeUser])
     * @param email - User email (which likes it).
     * @param sharedId - Shared id (see: [BaseShared.sharedId]).
     * @return String with result ("success" or some error)
     */
    suspend fun likeSharedView(
        sessionId: String, email: String, sharedId: Int
    ) = likeSharedCollection("View", sessionId, email, sharedId)

    /**
     * Add like to shared block count.
     * @param sessionId - Session id (see [authorizeUser])
     * @param email - User email (which likes it).
     * @param sharedId - Shared id (see: [BaseShared.sharedId]).
     * @return String with result ("success" or some error)
     */
    suspend fun likeSharedBlock(
        sessionId: String, email: String, sharedId: Int
    ) = likeSharedCollection("Block", sessionId, email, sharedId)

    /**
     * Add like to shared block count.
     * @param sessionId - Session id (see [authorizeUser])
     * @param email - User email (which likes it).
     * @param sharedId - Shared id (see: [BaseShared.sharedId]).
     * @return String with result ("success" or some error)
     */
    suspend fun likeSharedMoreblock(
        sessionId: String, email: String, sharedId: Int
    ) = likeSharedCollection("MoreBlock", sessionId, email, sharedId)

    /**
     * Uploads shared collection file.
     */
    suspend fun uploadSharedCollectionFile(sessionId: String, fileName: String, tag: String) =
        httpClient.postRequest<String>("$baseUrl/uploadShare${tag}File.do") {
            ContentDisposition("session_id")
        }

    private suspend fun removeCollection(tag: String, sharedId: Int, sessionId: String, email: String) =
        httpClient.postRequest<String>("$baseUrl/reqDeleteShared${tag}.do") {
            body = buildJsonObject {
                put("session_id", sessionId)
                put("login_id", email)
                put("shared_id", sharedId)
            }
        }

    /**
     * Removes shared view by [sharedId].
     * @param sharedId - unique shared collection id.
     * @param sessionId - your authorization token.
     * @param email - your email.
     */
    suspend fun removeSharedView(sharedId: Int, sessionId: String, email: String) =
        removeCollection("View", sharedId, sessionId, email)

    /**
     * Removes shared moreblock by [sharedId].
     * @param sharedId - unique shared collection id.
     * @param sessionId - your authorization token.
     * @param email - your email.
     */
    suspend fun removeSharedMoreblock(sharedId: Int, sessionId: String, email: String) =
        removeCollection("MoreBlock", sharedId, sessionId, email)

    /**
     * Removes shared block by [sharedId].
     * @param sharedId - unique shared collection id.
     * @param sessionId - your authorization token.
     * @param email - your email.
     */
    suspend fun removeSharedBlock(sharedId: Int, sessionId: String, email: String) =
        removeCollection("Block", sharedId, sessionId, email)

}