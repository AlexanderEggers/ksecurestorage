package io.github.alexandereggers.ksecurestorage

interface IKSecureStorage {
    /**
     * Writes data to the disk, using SharedPreferences or KeyChain, depending on the platform.
     *
     * @param key A string that will be associated to the value for later retrieval.
     * @param value The data to store.
     */
    fun setItem(key: String, value: String?)

    /**
     * Retrieves data from the disk, using SharedPreferences or KeyChain, depending on the platform
     * and returns it as the specified type.
     *
     * @param key String value that is associated to a value.
     * @return A string value if the key/value pair exists, otherwise null.
     */
    fun getItem(key: String): String?

    /**
     * Deletes data from the disk, using SharedPreferences or KeyChain, depending on the platform.
     * @param key String value that is associated to a value.
     */
    fun removeItem(key: String)

    /**
     * Clears all data from disk, using SharedPreferences or KeyChain, depending on the platform.
     */
    fun clear()
}