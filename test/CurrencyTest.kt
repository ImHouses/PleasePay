package com.juancasasm

import com.juancasasm.models.Currency
import com.juancasasm.models.dao.Currencies
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.Test
import kotlin.test.assertTrue

class CurrencyTest {

    init {
        DbSettings.init()
    }

    @Test
    fun `test get currencies`() {
        transaction {
            val query: Query = Currencies.selectAll()
            val expectedValues = setOf(
                Currency(1, "Mexican Peso", "MXN"),
                Currency(2, "US Dollar", "USD"),
                Currency(3, "Euro", "EUR")
            )
            val resultList = query.toList()
            for (result in resultList) {
                val resultCurrency = Currency(
                    id = result[Currencies.id],
                    name = result[Currencies.name],
                    symbol = result[Currencies.symbol]
                )
                assertTrue(resultCurrency in expectedValues)
            }
        }
    }

    @Test
    fun `test get currency by symbol`() {
        transaction {
            val query: Query = Currencies.select { Currencies.symbol eq "MXN" }
            assertTrue(query.count() == 1)
            val result = query.toList()[0]
            assertTrue(result[Currencies.id] == 1)
            assertTrue(result[Currencies.name] == "Mexican Peso")
        }
    }
}