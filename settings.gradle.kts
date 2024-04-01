enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KSecureStorage_Project"

include(":lib")
project(":lib").name = "ksecurestorage"

include(":example:androidApp")
include(":example:shared")
