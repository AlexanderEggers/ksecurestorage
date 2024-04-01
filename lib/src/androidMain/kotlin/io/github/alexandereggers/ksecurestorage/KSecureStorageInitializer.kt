package io.github.alexandereggers.ksecurestorage

import android.content.Context
import androidx.startup.Initializer

internal lateinit var applicationContext: Context
    private set

object KSecureStorageContext

@Suppress("unused")
class KSecureStorageInitializer: Initializer<KSecureStorageContext> {
    override fun create(context: Context): KSecureStorageContext {
        applicationContext = context.applicationContext
        return KSecureStorageContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}