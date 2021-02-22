package io.sketchware.api.java

import io.sketchware.api.SketchwareAPIClient
import io.sketchware.api.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SketchcodeJavaAPIClient(
    private val baseUrl: String = "http://sketchware.io",
    loggerEnabled: Boolean = false, override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope {
    private val client = SketchwareAPIClient(baseUrl, loggerEnabled)

    fun getMinSketchwareVersion(responseCallback: ResponseCallback<Int>) = launch {
        try {
            responseCallback.onSuccess(client.getMinSketchwareVersion())
        } catch (e: Exception) {
            responseCallback.onError(e)
        }
    }

    fun getRecentSharedSharedViews(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getRecentSharedViews(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getRecentSharedSharedMoreblocks(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getRecentSharedMoreblocks(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getRecentSharedSharedBlocks(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getRecentSharedBlocks(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getMostDownloadedSharedViews(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getMostDownloadedSharedViews(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getMostDownloadedSharedMoreblocks(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getMostDownloadedSharedMoreblocks(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getMostDownloadedSharedBlocks(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getMostDownloadedSharedBlocks(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getMostLikedSharedBlocks(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getMostLikedSharedBlocks(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getMostLikedSharedViews(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getMostLikedSharedViews(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getMostLikedSharedMoreblocks(
        itemsToLoad: Int, index: Int, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.getMostLikedSharedMoreblocks(itemsToLoad, index)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedViewFile(
        sharedId: Int, fileName: String, responseCallback: ResponseCallback<ByteArray>
    ) = launch {
        client.getSharedViewFile(sharedId, fileName)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedBlockFile(
        sharedId: Int, fileName: String, responseCallback: ResponseCallback<ByteArray>
    ) = launch {
        client.getSharedBlockFile(sharedId, fileName)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedMoreblockFile(
        sharedId: Int, fileName: String, responseCallback: ResponseCallback<ByteArray>
    ) = launch {
        client.getSharedMoreblockFile(sharedId, fileName)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedViewDetails(
        sharedId: Int, responseCallback: ResponseCallback<SharedDetails>
    ) = launch {
        client.getSharedViewDetails(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedMoreblockDetails(
        sharedId: Int, responseCallback: ResponseCallback<SharedDetails>
    ) = launch {
        client.getSharedMoreblockDetails(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedBlockDetails(
        sharedId: Int, responseCallback: ResponseCallback<SharedDetails>
    ) = launch {
        client.getSharedBlockDetails(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedViewComments(
        sharedId: Int, responseCallback: ResponseCallback<List<SharedComment>>
    ) = launch {
        client.getSharedViewComments(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedMoreblockComments(
        sharedId: Int, responseCallback: ResponseCallback<List<SharedComment>>
    ) = launch {
        client.getSharedMoreblockComments(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedBlockComments(
        sharedId: Int, responseCallback: ResponseCallback<List<SharedComment>>
    ) = launch {
        client.getSharedBlockComments(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedViewTags(
        sharedId: Int, responseCallback: ResponseCallback<List<SharedTag>>
    ) = launch {
        client.getSharedViewTags(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedMoreblockTags(
        sharedId: Int, responseCallback: ResponseCallback<List<SharedTag>>
    ) = launch {
        client.getSharedMoreblockTags(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getSharedBlockTags(
        sharedId: Int, responseCallback: ResponseCallback<List<SharedTag>>
    ) = launch {
        client.getSharedBlockTags(sharedId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }


    fun getExportedProject(
        urlId: Int, responseCallback: ResponseCallback<List<SharedProject>>
    ) = launch {
        client.getExportedProject(urlId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getProjectExportedFile(
        urlId: Int, fileName: String, userId: Int, responseCallback: ResponseCallback<ByteArray>
    ) = launch {
        client.getProjectExportedFile(fileName, userId, urlId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun addCommentToSharedView(
        sessionId: String,
        email: String,
        sharedId: Int,
        comment: String,
        responseCallback: ResponseCallback<String>
    ) = launch {
        client.addCommentToSharedView(sessionId, email, sharedId, comment)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun addCommentToSharedBlock(
        sessionId: String,
        email: String,
        sharedId: Int,
        comment: String,
        responseCallback: ResponseCallback<String>
    ) = launch {
        client.addCommentToSharedBlock(sessionId, email, sharedId, comment)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun addCommentToSharedMoreblock(
        sessionId: String,
        email: String,
        sharedId: Int,
        comment: String,
        responseCallback: ResponseCallback<String>
    ) = launch {
        client.addCommentToSharedMoreblock(sessionId, email, sharedId, comment)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun searchViewsByTag(
        itemsToLoad: Int, index: Int, tag: String, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.searchViewsByTag(itemsToLoad, index, tag)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun searchMoreblocksByTag(
        itemsToLoad: Int, index: Int, tag: String, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.searchMoreblocksByTag(itemsToLoad, index, tag)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun searchBlocksByTag(
        itemsToLoad: Int, index: Int, tag: String, responseCallback: ResponseCallback<List<BaseShared>>
    ) = launch {
        client.searchBlocksByTag(itemsToLoad, index, tag)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getAllTags(responseCallback: ResponseCallback<List<SharedTag>>) = launch {
        client.getAllTags()
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun removeSharedViewComment(
        sessionId: String,
        email: String,
        sharedId: Int,
        commentId: Int,
        responseCallback: ResponseCallback<String>
    ) = launch {
        client.removeSharedViewComment(sessionId, email, sharedId, commentId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun removeSharedBlockComment(
        sessionId: String,
        email: String,
        sharedId: Int,
        commentId: Int,
        responseCallback: ResponseCallback<String>
    ) = launch {
        client.removeSharedBlockComment(sessionId, email, sharedId, commentId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun removeSharedMoreblockComment(
        sessionId: String,
        email: String,
        sharedId: Int,
        commentId: Int,
        responseCallback: ResponseCallback<String>
    ) = launch {
        client.removeSharedMoreblockComment(sessionId, email, sharedId, commentId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun authorizeUser(
        email: String,
        deviceId: String,
        accessToken: String,
        gcmId: String,
        responseCallback: ResponseCallback<AuthorizedModel>
    ) = launch {
        client.authorizeUser(email, deviceId, accessToken, gcmId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getUserExportedApks(
        sessionId: String, email: String, responseCallback: ResponseCallback<List<ExportedApk>>
    ) = launch {
        client.getUserExportedApks(sessionId, email)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun getUserExportedProjects(
        sessionId: String, email: String, responseCallback: ResponseCallback<List<ExportedApk>>
    ) = launch {
        client.getUserExportedProjects(sessionId, email)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun removeExportedProject(
        sessionId: String, email: String, urlId: Int, responseCallback: ResponseCallback<String>
    ) = launch {
        client.removeExportedProject(sessionId, email, urlId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }

    fun removeExportedApk(
        sessionId: String, email: String, urlId: Int, responseCallback: ResponseCallback<String>
    ) = launch {
        client.removeExportedApk(sessionId, email, urlId)
            .success(responseCallback::onSuccess)
            .error(responseCallback::onError)
    }


}