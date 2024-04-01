package com.alexandereggers.ksecurestorage

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KSecureStorage() : IKSecureStorage

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and returns a boolean if the key/value pair exists.
 *
 * @param key String value that is associated to a value.
 * @return True if the key/value pair exists, otherwise false.
 */
fun KSecureStorage.hasItem(key: String): Boolean {
    return getItem(key) != null
}