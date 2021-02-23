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
    maven("https://dl.kotlingang.fun")
}
dependencies {
    implementation("io.sketchware.api:SketchwareAPI:1.0.5")
}
```

For Java, you also need to add the Java wrapper:

```groovy
repositories {
    maven { url 'https://dl.kotlingang.fun' }
}
dependencies {
    implementation "io.sketchware.api:SketchwareJavaAPI:1.0.5"
}
```
You can also check 
[it](https://github.com/y9neon/SketchwareAPI/tree/master/tutorials/How%20to%20use%20library%20in%20java) out.

# 📞 Contacts

Below are links to sources where you can contact for questions about the library or just chat (
like [@sketchware_community](https://t.me/sketchware_community)).

- My telegram - [@y9neon](https://t.me/y9neon).
- Sketchware community group - [@sketchware_community](https://t.me/sketchware_community).
