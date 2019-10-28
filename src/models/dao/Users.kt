package com.juancasasm.models.dao

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Users : Table("account_user") {
    val id: Column<Int> = integer("id")
    val username: Column<String> = varchar("username", 50)
    val firstName: Column<String> = varchar("first_name", 50)
    val lastName: Column<String> = varchar("last_name", 50)
    val gender: Column<String> = varchar("gender", 10)
    val birthDate: Column<DateTime> = date("birth_date")
    val email: Column<String> = varchar("email", 100)
    val profilePhotoUrl: Column<String?> = varchar("profile_photo_url", 500).nullable()
}