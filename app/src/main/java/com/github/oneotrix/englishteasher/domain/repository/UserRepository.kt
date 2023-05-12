package com.github.oneotrix.englishteasher.domain.repository

import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword

interface UserRepository {

    fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword)

    fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword)
}