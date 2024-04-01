package io.github.alexandereggers.ksecurestorage.example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform