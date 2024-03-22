package dev.kichan.composecomunity

import java.time.LocalDateTime
import java.util.Date
import kotlin.random.Random

fun randomHash(STRING_LENGTH : Int = 10) : String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..STRING_LENGTH)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")
}