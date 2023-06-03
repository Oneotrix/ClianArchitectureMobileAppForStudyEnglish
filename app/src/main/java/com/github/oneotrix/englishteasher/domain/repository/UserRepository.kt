package com.github.oneotrix.englishteasher.domain.repository

import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.*

interface UserRepository {

    fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword)

    fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword)

    fun sendUserDataToRegInFirebase(userDataReg: UserDataReg) : RegistrationResult

    fun sendEmailForRecoveryPassword(userEmail: UserEmail)

    fun sendSecretCodeForRecoveryPassword(secretCode: SecretCode)

    fun saveNewPassword(password: Password)

    fun getUserDataForAuthFromDataBase(userDatabase: AppDatabase): UserLoginAndPassword
}