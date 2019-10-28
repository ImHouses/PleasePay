package com.juancasasm

import com.juancasasm.util.Generators
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GeneratorsTest {

    @Test
    fun `generate random string`() {
        for (i in 1..10000) {
            val generatedString = Generators.generateRandomString(i)
            assertEquals(generatedString.length, i)
        }
    }
}