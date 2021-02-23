# How to use library in Java

The library was originally intended only for Kotlin, but the ability to use the library in Java has recently been added.
To use it, you need to add the following dependency:

```groovy
repositories {
    maven { url 'https://dl.kotlingang.fun' }
}
dependencies {
    implementation "io.sketchware.api:SketchwareJavaAPI:1.0.4"
}
```

This version differs only in that you will need to use callbacks. An example in Kotlin:

```kotlin
client.getRecentSharedMoreblocks(20, 0).success { list: List<BaseShared> ->
    println(list) // successfully loaded
}.error {
    // some error occurred
}
```

On Java it will be like:

```java
SketchwareJavaAPIClient client=new SketchwareJavaAPIClient();
        client.getAllTags(new ResponseCallback<>(){
@Override
public void onSuccess(List<SharedTag> body){
        System.out.println(body); // successfully
        }

@Override
public void onError(@NotNull Throwable throwable){
        throwable.printStackTrace(); // some error has occurred.
        }
        });
```

Everything else is done the same way! So tutorials for Kotlin are relevant for Java too! Good luck with your use!