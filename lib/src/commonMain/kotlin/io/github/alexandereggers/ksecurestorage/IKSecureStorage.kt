package io.github.alexandereggers.ksecurestorage

interface IKSecureStorage {
    /**
     * Writes a [String] to the disk, using SharedPreferences or KeyChain, depending on the
     * platform.
     *
     * @param key [String] key that is associated to the value.
     * @param value [String] value to store.
     */
    fun setItem(key: String, value: String?)

    /**
     * Retrieves a [String] from the disk, using SharedPreferences or KeyChain, depending on the
     * platform.
     *
     * @param key [String] key that is associated to a value.
     * @return A [String] value if the key/value pair exists, otherwise null.
     */
    fun getItem(key: String): String?

    /**
     * Deletes a [String] from the disk, using SharedPreferences or KeyChain, depending on the
     * platform.
     *
     * @param key [String] key that is associated to a value.
     */
    fun removeItem(key: String)

    /**
     * Clears all data from disk, using SharedPreferences or KeyChain, depending on the platform.
     */
    fun clear()
}