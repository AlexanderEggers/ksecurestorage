package io.github.alexandereggers.ksecurestorage

import kotlinx.cinterop.*
import platform.CoreFoundation.*
import platform.Foundation.*
import platform.Security.*

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
actual class KSecureStorage actual constructor() : IKSecureStorage {

    override fun setItem(key: String, value: String?) {
        val query = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to CFBridgingRetain(key),
            kSecValueData to CFBridgingRetain(value)
        )

        SecItemDelete(query)
        val status = SecItemAdd(query, null)

        if (status == errSecDuplicateItem) {
            throw Exception("Item with key $key already exists")
        } else if (status != errSecSuccess) {
            throw Exception("Error adding item with key $key")
        }
    }

    override fun getItem(key: String): String? {
        val getQuery = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to CFBridgingRetain(key),
            kSecReturnData to kCFBooleanTrue,
            kSecMatchLimit to kSecMatchLimitOne
        )

        memScoped {
            val result = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(getQuery, result.ptr)

            if (status == errSecItemNotFound) {
                return null
            } else if (status != errSecSuccess) {
                throw Exception("Error getting item with key $key")
            } else {
                val data = CFBridgingRelease(result.value) as NSData
                return NSString.create(data, NSUTF8StringEncoding).toString()
            }
        }
    }

    override fun removeItem(key: String) {
        val deleteQuery = query(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to CFBridgingRetain(key),
            kSecReturnData to kCFBooleanTrue,
        )

        val status = SecItemDelete(deleteQuery)

        if (status != errSecSuccess) {
            throw Exception("Error deleting item with key $key and status $status")
        }
    }

    override fun clear() {
        val query = query(
            kSecClass to kSecClassGenericPassword
        )

        val status = SecItemDelete(query)

        if (status != errSecSuccess) {
            throw Exception("Error clearing items with status $status")
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun query(vararg pairs: Pair<CFStringRef?, CFTypeRef?>): CFMutableDictionaryRef? {
    val map = mapOf(*pairs)
    return CFDictionaryCreateMutable(
        null, map.size.convert(), null, null
    ).apply {
        map.forEach { CFDictionaryAddValue(this, it.key, it.value) }
    }.apply {
        CFAutorelease(this)
    }
}