plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

group = "io.github.alexandereggers"
version = (System.getenv("RELEASE_TAG_NAME") ?: "v0.0.0-local").replace("v", "")

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
        publishLibraryVariants("release")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            this.baseName = baseName
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.security.crypto)
                implementation(libs.androidx.startup.runtime)
            }
        }
    }

    task("testClasses")
}

android {
    namespace = "io.github.alexandereggers.ksecurestorage"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AlexanderEggers/ksecurestorage")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GH_USER")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GH_TOKEN")
            }
        }
    }
}