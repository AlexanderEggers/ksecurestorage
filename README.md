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
            implementation("io.github.alexandereggers:ksecurestorage:0.0.3")
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
/**
 * Save an item to the secure storage.
 * 
 * Note: if you want to store a different type than a string, you can use the setItem extension 
 * functions which covers other types.
 */
kSecureStorage.setItem("myItemKey", "someValue")

// Retrieve an item from the secure storage.
kSecureStorage.getItem("myItemKey")

// Delete an item from the secure storage.
kSecureStorage.removeItem("myItemKey")

// Clear all data from the secure storage.
kSecureStorage.clear()

// This library also includes a range of extension function to simplify the usage

// This function checks if an item exists in the secure storage.
kSecureStorage.hasItem("myItemKey")

// These functions retrieve an item from the secure storage and casts it to the desired type.
kSecureStorage.getString("myItemKey")
kSecureStorage.getInt("myItemKey")
kSecureStorage.getBoolean("myItemKey")
kSecureStorage.getFloat("myItemKey")
kSecureStorage.getDouble("myItemKey")
kSecureStorage.getLong("myItemKey")

/**
 * This is an an additional extension function which will make use of 
 * https://github.com/Kotlin/kotlinx.serialization to return the desired object type.
 * 
 * This function also takes optionally the [DeserializationStrategy] as a parameter.
 */
kSecureStorage.getTypedObject<MyObject>("myItemKey")
```

## License

KSecureStorage is available under the MIT license. See the [LICENSE](https://github.com/AlexanderEggers/ksecurestorage/blob/main/LICENSE) file for more info.

## Road to 1.0
- [x] Add iOS support
- [ ] Add mavenCentral support
- [ ] Add tests
