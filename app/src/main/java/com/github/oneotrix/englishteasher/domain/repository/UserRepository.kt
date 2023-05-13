package com.github.oneotrix.englishteasher.domain.repository

import com.github.oneotrix.englishteasher.domain.models.SecretCode
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.models.UserEmail
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword

interface UserRepository {

    fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword)

    fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword)

    fun sendUserDataToRegInFirebase(userDataReg: UserDataReg)

    fun sendEmailForRecoveryPassword(userEmail: UserEmail)

    fun sendSecretCodeForRecoveryPassword(secretCode: SecretCode)
}