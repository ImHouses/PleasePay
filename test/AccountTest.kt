package com.juancasasm

import com.juancasasm.models.dao.Accounts
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import kotlin.test.assertTrue

class AccountTest {

    init {
        DbSettings.init()
    }

    @Test
    fun `create account test`() {
        transaction {
            val query: Query = Accounts.selectAll()
            val rows = query.toList()
            assertTrue(rows.isEmpty())
            val newAccount = Accounts.insert {
                it[name] = "Test Account"
                it[description] = "This is a description of my test account."
                it[currencyId] = 1
            }
        }
    }
}