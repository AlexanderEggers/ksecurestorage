package io.github.alexandereggers.ksecurestorage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

private const val SHARED_PREFERENCES_FILENAME = "K_SECURE_STORAGE_ENCRYPTED_STORAGE_SHARED_PREF"

@Suppress("unused")
actual class KSecureStorage : IKSecureStorage {

    private var sharedPreferences: SharedPreferences? = null

    actual constructor() : this(applicationContext)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    constructor(context: Context) {
        try {
            val key = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            sharedPreferences = EncryptedSharedPreferences.create(
                context,
                SHARED_PREFERENCES_FILENAME,
                key,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (ex: Exception) {
            Log.e(
                KSecureStorage::class.simpleName,
                "Failed to create encrypted shared preferences! Failing back to standard SharedPreferences",
                ex
            )
            sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_FILENAME,
                Context.MODE_PRIVATE
            )
        }
    }

    private fun getSharedPreferences(): SharedPreferences {
        sharedPreferences?.let {
            return it
        } ?: run {
            throw NullPointerException("Could not initialize SharedPreferences")
        }
    }

    override fun setItem(key: String, value: String?) {
        val editor = getSharedPreferences().edit()
        editor.putString(key, value)
        val saved = editor.commit()
        if (!saved) {
            throw Exception("An error occurred while saving $key")
        }
    }

    override fun getItem(key: String): String? {
        return getSharedPreferences().getString(key, null)
    }

    override fun removeItem(key: String) {
        val editor = getSharedPreferences().edit()
        editor.remove(key)
        val saved = editor.commit()
        if (!saved) {
            throw Exception("An error occurred while removing $key")
        }
    }

    override fun clear() {
        val editor = getSharedPreferences().edit()
        editor.clear()
        val saved = editor.commit()
        if (!saved) {
            throw Exception("An error occurred while clearing SharedPreferences")
        }
    }
}