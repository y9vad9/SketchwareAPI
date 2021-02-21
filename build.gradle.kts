import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    `maven-publish`
}

group = "io.sketchware.api"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://sketchcode.fun/dl")
}

val ktorVersion = "1.5.1"

kotlin {
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:1.5.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
    }
}

val localProperties = project.rootProject.file("local.properties")
    .takeIf(File::exists)
    ?.let(File::getAbsolutePath)
    ?.let(::loadProperties)

publishing {
    apply(plugin = "maven-publish")
    publications {
        create<MavenPublication>("Deploy") {
            groupId = "io.sketchware.api"
            artifactId = "SketchwareAPI"
            version = "1.0"
        }
    }

    repositories {
        maven {
            name = "sketchware-api"
            url = uri(localProperties!!["serverURI"]!!)
            credentials {
                username = (localProperties["username"] as String?)!!
                password = (localProperties["password"] as String?)!!
            }
        }
    }

}

