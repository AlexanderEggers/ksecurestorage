# KSecureStorage

KSecureStorage is a Kotlin Multiplatform Mobile (KMM) library designed to provide a simple storage solution to handle sensitive information within Android and iOS applications. 
It seamlessly integrates with standard platform-specific encryption mechanisms (`EncryptedSharedPreferences` on Android and `Keychain` on iOS) to ensure data encryption and protection.

## Installation

```kotlin
// settings.gradle.kts or build.gradle.kts (root)
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/AlexanderEggers/ksecurestorage")
    }
}

// build.gradle.kts
sourceSets {
    val commonMain by getting {
        dependencies {
            implementation("io.github.alexandereggers:ksecurestorage:0.0.1")
        }
    }
}
```

## Initialization

`KSecureStorage` is the main class for dealing with the secure storage wrapper and should only be defined once in the app.

```kotlin
import io.github.alexandereggers.ksecurestorage.IKSecureStorage
import io.github.alexandereggers.ksecurestorage.KSecureStorage

// When using something like Koin
module {
    single<IKSecureStorage> { KSecureStorage() }
}

// But you can also define that as a constant somewhere
val kSecureStorage = KSecureStorage()
```

## Usage

```kotlin
import io.github.alexandereggers.ksecurestorage.hasItem

// save an item
kSecureStorage.setItem("myItemKey", "someValue")

// retrieve an item
kSecureStorage.getItem("myItemKey")

// extension function to check if an item is existing in the secure storage
kSecureStorage.hasItem("myItemKey")

// delete an item
kSecureStorage.removeItem("myItemKey")

// clear all data from the secure storage
kSecureStorage.clear()
```

## License

KSecureStorage is available under the MIT license. See the [LICENSE](https://github.com/AlexanderEggers/ksecurestorage/blob/main/LICENSE) file for more info.

## Road to 1.0
- [ ] Add iOS support
- [ ] Add mavenCentral support
- [ ] Add tests
