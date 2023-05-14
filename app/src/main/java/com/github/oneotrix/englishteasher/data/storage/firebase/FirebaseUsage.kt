package com.github.oneotrix.englishteasher.data.storage.firebase

import com.github.oneotrix.englishteasher.data.storage.models.RegistrationResult
import com.github.oneotrix.englishteasher.data.storage.models.User


interface FirebaseUsage {

    fun createUsersDatabase()

    fun createUser(user : User) : RegistrationResult

    fun modifyUser(uid: String ,user: User)

    fun deleteUser(uid: String)

    fun takeUserData(uid: String)

}