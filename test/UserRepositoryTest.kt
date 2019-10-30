package com.juancasasm

import com.juancasasm.datarepositories.users.UserRepository
import com.juancasasm.util.Generators
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime
import kotlin.test.*

class UserRepositoryTest {

    init {
        DbSettings.init()
    }

    private val repository = UserRepository()

    @Test
    fun `create new user test`() = runBlocking {
        val fields = generateRandomUser()
        val createdUser = repository.createUser(fields)
        assertNotNull(createdUser)
        assertEquals(createdUser.username, fields["username"])
        assertEquals(createdUser.firstName, fields["first_name"])
        assertEquals(createdUser.lastName, fields["last_name"])
        assertEquals(createdUser.gender, fields["gender"])
        assertEquals(createdUser.birthDate, fields["birth_date"])
        assertEquals(createdUser.email, fields["email"])
    }

    @Test
    fun `find user by identifier fields`() = runBlocking {
        val fields = generateRandomUser()
        val createdUser = repository.createUser(fields) ?: fail()
        val userByEmail = repository.getUserByField(UserRepository.UserField.EMAIL, createdUser.email)
        assertEquals(createdUser, userByEmail)
        val userByUsername = repository.getUserByField(UserRepository.UserField.USERNAME, createdUser.username)
        assertEquals(userByUsername, createdUser)
        val userById = repository.getUserByField(UserRepository.UserField.ID, createdUser.id!!)
        assertEquals(userById, createdUser)
    }

    @Test
    fun `update user test`() = runBlocking {
        var fields = mutableMapOf<String, Any>()
        assertFails {
            repository.updateUser(mapOf<String, Any>())
        }
        assertFails {
            fields["username"] = Generators.generateRandomString(5)
            repository.updateUser(fields)
        }
        assertFails {
            fields["first_name"] = Generators.generateRandomString(20)
            repository.updateUser(fields)
        }
        assertFails {
            fields["last_name"] = Generators.generateRandomString(20)
            repository.updateUser(fields)
        }
        assertFails {
            fields["gender"] = "M"
            repository.updateUser(fields)
        }
        assertFails {
            fields["birth_date"] = DateTime.now()
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0)
                .withMillisOfSecond(0)
            repository.updateUser(fields)
        }
        assertFails {
            fields["email"] = "${Generators.generateRandomString(5)}@gmail.com"
            repository.updateUser(fields)
        }
        assertFails {
            repository.updateUser(fields)
        }
        fields = generateRandomUser() as MutableMap<String, Any>
        val createdUser = repository.createUser(fields) ?: fail()
        fields["id"] = createdUser.id as Any
        fields["first_name"] = Generators.generateRandomString(6)
        var updatedUser = repository.updateUser(fields) ?: fail()
        assertNotEquals(createdUser.firstName, updatedUser.firstName)
        fields["last_name"] = Generators.generateRandomString(6)
        updatedUser = repository.updateUser(fields) ?: fail()
        assertNotEquals(createdUser.lastName, updatedUser.lastName)
        fields["gender"] = "F"
        updatedUser = repository.updateUser(fields) ?: fail()
        assertNotEquals(createdUser.gender, updatedUser.gender)
        fields["birth_date"] = DateTime.now().withYear(1997)
        updatedUser = repository.updateUser(fields) ?: fail()
        assertNotEquals(createdUser.birthDate, updatedUser.birthDate)
        fields["email"] = "${Generators.generateRandomString(5)}@gmail.com"
        updatedUser = repository.updateUser(fields) ?: fail()
        assertNotEquals(createdUser.email, updatedUser.email)
        fields["profile_photo_url"] = "http://google.com"
        updatedUser = repository.updateUser(fields) ?: fail()
        assertNotNull(updatedUser.profilePhotoUrl)
        assertNotEquals(createdUser.profilePhotoUrl, updatedUser.profilePhotoUrl)
    }


    private fun generateRandomUser(): Map<String, *> {
        val fakeUserName = Generators.generateRandomString(5)
        val firstName = Generators.generateRandomString(20)
        val lastName = Generators.generateRandomString(20)
        val gender = "M"
        val birthDate = DateTime.now()
            .withHourOfDay(0)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0)
            .withMillisOfSecond(0)
        val email = "${Generators.generateRandomString(5)}@gmail.com"
        return mapOf<String, Any>(
            "username" to fakeUserName,
            "first_name" to firstName,
            "last_name" to lastName,
            "gender" to gender,
            "birth_date" to birthDate,
            "email" to email
        )
    }
}