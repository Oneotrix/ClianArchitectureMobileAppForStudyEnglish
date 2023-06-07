package com.github.oneotrix.englishteasher.domain.repository

import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.*

interface UserRepository {

     suspend fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword) : String?

    fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword)

    suspend fun sendUserDataToRegInFirebase(userDataReg: UserDataReg) : String?

    fun sendEmailForRecoveryPassword(userEmail: UserEmail)

    fun sendSecretCodeForRecoveryPassword(secretCode: SecretCode)

    fun saveNewPassword(password: Password)

    fun getUserDataForAuthFromDataBase(): UserLoginAndPassword?
}