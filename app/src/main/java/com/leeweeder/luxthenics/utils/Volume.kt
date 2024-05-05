package com.leeweeder.luxthenics.utils

sealed class Volume {
    data class Fixed(val volume: Long): Volume()
    data class Range(val from: Long, val to: Long): Volume()
}

