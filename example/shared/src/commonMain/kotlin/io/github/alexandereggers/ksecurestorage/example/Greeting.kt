package io.github.alexandereggers.ksecurestorage.example

import io.github.alexandereggers.ksecurestorage.KSecureStorage
import io.github.alexandereggers.ksecurestorage.getBoolean
import io.github.alexandereggers.ksecurestorage.hasItem
import io.github.alexandereggers.ksecurestorage.setItem

const val TEST_STORAGE_KEY = "hasDisplayedBefore"

class Greeting {
    private val platform: Platform = getPlatform()
    private val kSecureStorage = KSecureStorage()

    fun greet(): String {
        val hasDisplayedBefore = kSecureStorage.getBoolean(TEST_STORAGE_KEY) ?: false

        return if (hasDisplayedBefore) {
            kSecureStorage.removeItem(TEST_STORAGE_KEY)
            "Welcome back, ${platform.name}!"
        } else {
            kSecureStorage.setItem(TEST_STORAGE_KEY, true)
            "Hello, ${platform.name}!"
        }
    }
}