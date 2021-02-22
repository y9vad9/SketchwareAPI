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
val list: List<BaseShared>
client.getSharedMoreblockDetails(list[0].sharedId).success {
    println(it.blockName!!) // block name not null if there is a moreblock/block
}
```

Also, we can get comments in this shared collection:

```kotlin
val list: List<BaseShared>
client.getSharedMoreblockComments(list[0].sharedId).success { list: List<SharedComment> ->
    list.forEach {
        println("${it.userAlias}: ${it.comment}") // User name: comment value.
    }
}
```

You can do the same with Views and Blocks.
