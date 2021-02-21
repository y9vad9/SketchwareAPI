# 🚀 Sketchware API Library

A multi-platform library for interacting with the Sketchware API. At the moment, not all requests have been implemented,
but will be added upon [request](https://github.com/y9neon/SketchwareAPI/issues/new).

# 🧪 Examples
Let's get a list of the latest moreblocks:
```kotlin
client.getRecentSharedMoreblocks(20, 0).success { list: List<BaseShared> ->
    println(list) // successfully loaded
}.error {
    // some error occurred
}
```
Let's get some moreblock comments:
```kotlin
client.getSharedMoreblockDetails(__moreblockId__).success { body: SharedDetails ->  
    println(body)
}.error { 
    // an error has occurred
}
```

# 📐 Implementation
build.gradle.kts:
```kotlin
repositories {
    maven("https://sketchcode.fun/dl")
}
dependencies {
    implementation("io.sketchware.api:SketchwareAPI:1.0")
}
```

# 📞 Contacts

Below are links to sources where you can contact for questions about the library or just chat (
like [@sketchware_community](https://t.me/sketchware_community)).

- My telegram - [@y9neon](https://t.me/y9neon).
- Sketchware community group - [@sketchware_community](https://t.me/sketchware_community).