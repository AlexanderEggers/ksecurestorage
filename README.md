# KSecureStorage

KSecureStorage is a Kotlin Multiplatform Mobile (KMM) library designed to provide a very simple solution to handle sensitive information within Android and iOS applications. 
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
            implementation("io.github.alexandereggers:ksecurestorage:<version>")
        }
    }
}
```

## Initialization

```kotlin
import io.github.alexandereggers.ksecurestorage.IKSecureStorage
import io.github.alexandereggers.ksecurestorage.KSecureStorage

// When using something like Koin
module {
    single<IKSecureStorage> { KSecureStorage() }
}

// But you can also define that as a constant somewhere
const val kSecureStorage = KSecureStorage()
```

## Usage

```kotlin
import io.github.alexandereggers.ksecurestorage.hasItem

// save an item
kSecureStorage.setItem("mItemKey", "someValue")

// retrieve an item
kSecureStorage.getItem("myItemKey")

// extension function to check if an item is existing in the secure storage
kSecureStorage.hasItem("myItemKey")

// delete an item
kSecureStorage.removeItem("mItemKey")

// clear all data from the secure storage
kSecureStorage.clear()
```

When `IKSecureStorage` interface which also allows much simpler mocking in your tests.

## License

KSecureStorage is available under the MIT license. See the [LICENSE](https://github.com/AlexanderEggers/ksecurestorage/blob/main/LICENSE) file for more info.

## Road to 1.0
- [ ] Add iOS support
- [ ] Add mavenCentral support
- [ ] Add tests
