# How to get project and save it.
Before starting, I would like to say that it would be nice if we understood
the minimum architecture of Sketchware and what, for what is responsible.
You can look at my [repository](https://github.com/y9neon/SketchwareManager) for working 
with Collections / Projects and see how everything works.

To get a project, you need to know its id. 
Unfortunately, you need to parse the link like sketchware.io/import.jsp?id=Gh1D personally 
(if you know only the link, but if you have url_id it is unnecessary)
and convert it to a digital identifier.
Let's start:
```kotlin
val client = SketchwareAPIClient()
client.getProject(_project_url_id_).success { list: List<SharedProject> ->
    if(list.isEmpty())
        println("Project with this id is not found!")
    else println(list[0].appName) // prints app name.
}.error { t: Throwable ->
    // an error has occurred
    t.printStackTrace()
}
```
You can get file content of some project
(Project contains `project`, `data.zip`, `res_image.zip`, `res_font.zip`, `res_sound.zip` files).
```kotlin
client.getProjectExportedFile("data.zip", _project_owner_id_, _project_url_id_).success { bytes: ByteArray ->
    File("..").writeBytes(bytes)
}.error { t: Throwable ->
    // error: file with this name not found or project with this id.
    t.printStackTrace()
}
```