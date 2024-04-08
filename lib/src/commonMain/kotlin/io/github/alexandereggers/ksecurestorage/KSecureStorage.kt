package io.github.alexandereggers.ksecurestorage

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KSecureStorage() : IKSecureStorage

/**
 * Writes a [Boolean] to the disk, using SharedPreferences or KeyChain, depending on the platform.
 *
 * @param key [String] key that is associated to the value.
 * @param value [Boolean] value to store.
 */
fun IKSecureStorage.setItem(key: String, value: Boolean) {
    setItem(key, value.toString())
}

/**
 * Writes a [Number] to the disk, using SharedPreferences or KeyChain, depending on the platform.
 *
 * @param key [String] key that is associated to the value.
 * @param value [Number] value to store.
 */
fun IKSecureStorage.setItem(key: String, value: Number) {
    setItem(key, value.toString())
}

/**
 * Writes a [T] to the disk, using SharedPreferences or KeyChain, depending on the platform.
 *
 * @param key [String] key that is associated to the value.
 * @param value [T] value to store.
 */
inline fun <reified T> IKSecureStorage.setItem(key: String, value: T) {
    setItem(key, Json.encodeToString(value))
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and returns a [Boolean] if the key/value pair exists.
 *
 * @param key [String] value that is associated to a value.
 * @return True if the key/value pair exists, otherwise false.
 */
fun IKSecureStorage.hasItem(key: String): Boolean {
    return getItem(key) != null
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and return a [Boolean] if the key/value pair exists, otherwise null.
 *
 * @param key [String] value that is associated to a value.
 * @return [Boolean] value if the key/value pair exists, otherwise null.
 */
fun IKSecureStorage.getBoolean(key: String): Boolean? {
    return getItem(key)?.toBooleanStrictOrNull()
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and return a [Int] if the key/value pair exists, otherwise null.
 *
 * @param key [String] value that is associated to a value.
 * @return [Int] value if the key/value pair exists, otherwise null.
 */
fun IKSecureStorage.getInt(key: String): Int? {
    return getItem(key)?.toIntOrNull()
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and return a [Long] if the key/value pair exists, otherwise null.
 *
 * @param key [String] value that is associated to a value.
 * @return [Long] value if the key/value pair exists, otherwise null.
 */
fun IKSecureStorage.getLong(key: String): Long? {
    return getItem(key)?.toLongOrNull()
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and return a [Float] if the key/value pair exists, otherwise null.
 *
 * @param key [String] value that is associated to a value.
 * @return [Float] value if the key/value pair exists, otherwise null.
 */
fun IKSecureStorage.getFloat(key: String): Float? {
    return getItem(key)?.toFloatOrNull()
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and return a [Double] if the key/value pair exists, otherwise null.
 *
 * @param key [String] value that is associated to a value.
 * @return [Double] value if the key/value pair exists, otherwise null.
 */
fun IKSecureStorage.getDouble(key: String): Double? {
    return getItem(key)?.toDoubleOrNull()
}

/**
 * This function is doing the same as [KSecureStorage.getItem].
 *
 * @param key [String] value that is associated to a value.
 * @return [String] value if the key/value pair exists, otherwise null.
 */
fun IKSecureStorage.getString(key: String): String? {
    return getItem(key)
}

/**
 * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
 * and return a [T] if the key/value pair exists, otherwise null.
 *
 * @param key [String] value that is associated to a value.
 * @param jsonDeserializer Optional [DeserializationStrategy] to deserialize the JSON string.
 * @return Generic object [T] value if the key/value pair exists, otherwise null.
 */
inline fun <reified T> IKSecureStorage.getTypedObject(
    key: String,
    jsonDeserializer: DeserializationStrategy<T>? = null
): T? {
    return getItem(key)?.let { jsonValue ->
        Json.decodeFromString(
            jsonDeserializer ?: Json.serializersModule.serializer(),
            jsonValue
        )
    }
}