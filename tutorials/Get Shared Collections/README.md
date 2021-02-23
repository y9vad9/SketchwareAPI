# Get shared collections

Sketchware has a convenient system for sharing your custom views, moreblocks, and some kind of block group. To get the
recently published moreblocks, for example, we only need to call
the ```SketchcodeAPIClient.getRecentSharedMoreblocks(int itemsToLoad, int index)``` method.

```kotlin
val client = SketchwareAPIClient()
// will be loaded 20 moreblocks starts from 1 position.
client.getRecentMoreblocks(20, 0).success { list: List<BaseShared> ->
    println(list) // successfully loaded
}.error { t: Throwable ->
    t.printStackTrace() // error has occurred
}
```

Let's get shared moreblock details:

```kotlin
client.getSharedMoreblockDetails(list[0].sharedId).success {
    println(it.blockName!!) // block name not null if there is a moreblock/block
}
```

Also, we can get comments in this shared collection:

```kotlin
client.getSharedMoreblockComments(list[0].sharedId).success { list: List<SharedComment> ->
    list.forEach {
        println("${it.userAlias}: ${it.comment}") // User name: comment value.
    }
}
```
If you want so, you can save moreblock on their own. There are files with names - 
`more_block_data`, `spec_data`, `res_image.zip`, `res_font.zip`, `res_sound.zip` (last 3 may not exist).
```kotlin
client.getSharedMoreblockFile(list[0].sharedId, _file_name_).success { bytes: ByteArray ->
    File(_somewhere_).writeBytes(bytes)
}.error {
    // file not found
}
```


You can do the same with Views and Blocks.
