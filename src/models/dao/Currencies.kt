package com.juancasasm.models.dao

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Currencies : Table(name = "currency") {
    val id: Column<Int> = integer("id")
    val name: Column<String> = varchar("currency_name", 20)
    val symbol: Column<String> = varchar("symbol", 5)
}