package com.juancasasm.datarepositories.users

import com.juancasasm.models.User
import com.juancasasm.models.dao.Users
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import com.juancasasm.DbSettings.dbQuery

class UserRepository {

    enum class UserField(val column: Column<*>) {
        EMAIL(Users.email),
        USERNAME(Users.username),
        ID(Users.id)
    }

    companion object {
        fun fromTransactionSingle(row: ResultRow?): User? {
            row ?: return null
            return User(
                row[Users.id],
                row[Users.username],
                row[Users.firstName],
                row[Users.lastName],
                row[Users.gender],
                row[Users.birthDate],
                row[Users.email],
                row[Users.profilePhotoUrl]
            )
        }
    }

    suspend fun createUser(fields: Map<String, *>): User? = dbQuery {
        Users.insert {
            it[username] = fields["username"] as String
            it[firstName] = fields["first_name"] as String
            it[lastName] = fields["last_name"] as String
            it[gender] = fields["gender"] as String
            it[birthDate] = fields["birth_date"] as DateTime
            it[email] = fields["email"] as String
            it[profilePhotoUrl] = fields["profile_photo_url"] as? String
        }
        val added = Users.select { Users.username eq fields["username"] as String }.singleOrNull()
        fromTransactionSingle(added)
    }


    suspend fun getUserByField(field: UserField, fieldValue: Any): User? = dbQuery {
        val added = Users.select {
            when (field.column) {
                Users.email -> Users.email eq fieldValue as String
                Users.username -> Users.username eq fieldValue as String
                else -> Users.id eq fieldValue as Int
            }
        }.singleOrNull()
        fromTransactionSingle(added)
    }

    suspend fun updateUser(fields: Map<String, *>): User? = dbQuery {
        require(!(!fields.containsKey("username") || !fields.containsKey("first_name") || !fields.containsKey("last_name")
                || !fields.containsKey("gender") || !fields.containsKey("birth_date") || !fields.containsKey("email")
                || !fields.containsKey("id"))
        ) { "Invalid fields parameter" }
        Users.update({Users.id eq fields["id"] as Int}) {
            it[username] = fields["username"] as String
            it[firstName] = fields["first_name"] as String
            it[lastName] = fields["last_name"] as String
            it[gender] = fields["gender"] as String
            it[birthDate] = fields["birth_date"] as DateTime
            it[email] = fields["email"] as String
            it[profilePhotoUrl] = fields["profile_photo_url"] as? String
        }
        fromTransactionSingle(Users.select { Users.id eq fields["id"] as Int}.singleOrNull())
    }
}