package com.juancasasm

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DbSettings {

    fun init() {
        // Replace this for a generic approach getting this variables from a config file.
        val host = "localhost"
        val port = 5432
        val dbName = "pleasepay_test"
        val url = "jdbc:postgresql://$host:$port/$dbName"
        val user = "jcasas"
        val password = ""
        val dataSource = HikariConfig().run {
            jdbcUrl = url
            username = user
            this.password = password
            HikariDataSource(this)
        }
        Database.connect(dataSource)
    }

    suspend fun <T> dbQuery(block: ()->T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}