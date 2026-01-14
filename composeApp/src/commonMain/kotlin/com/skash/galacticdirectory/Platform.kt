package com.skash.galacticdirectory

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform