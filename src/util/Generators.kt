package com.juancasasm.util

import java.util.Random

object Generators {

    private val random: Random = Random()
    private val alphabet = "abcdefghiklmnopqrstxwyzABCDEFGHIJKLMNOPQRSTXWYZ12345678"

    fun generateRandomString(length: Int): String {
        val buffer = StringBuffer()
        for (i in 0 until length) {
            buffer.append(alphabet[random.nextInt(alphabet.length)])
        }
        return buffer.toString()
    }
}