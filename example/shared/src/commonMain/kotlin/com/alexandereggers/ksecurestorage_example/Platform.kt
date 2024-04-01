package com.alexandereggers.ksecurestorage_example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform