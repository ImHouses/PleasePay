package com.juancasasm.models

import org.joda.time.DateTime

data class User(
    val id: Int?,
    val username: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val birthDate: DateTime,
    val email: String,
    val profilePhotoUrl: String?
)