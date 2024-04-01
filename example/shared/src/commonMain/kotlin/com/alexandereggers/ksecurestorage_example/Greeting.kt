package com.alexandereggers.ksecurestorage_example

import com.alexandereggers.ksecurestorage.KSecureStorage
import com.alexandereggers.ksecurestorage.hasItem

const val TEST_STORAGE_KEY = "hasDisplayedBefore"

class Greeting {
    private val platform: Platform = getPlatform()
    private val kSecureStorage = KSecureStorage()

    fun greet(): String {
        val hasDisplayedBefore = kSecureStorage.hasItem(TEST_STORAGE_KEY)

        return if(hasDisplayedBefore) {
            kSecureStorage.removeItem(TEST_STORAGE_KEY)
            "Welcome back, ${platform.name}!"
        } else {
            kSecureStorage.setItem(TEST_STORAGE_KEY, "${true}")
            "Hello, ${platform.name}!"
        }
    }
}