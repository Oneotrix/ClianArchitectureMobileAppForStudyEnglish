package com.github.oneotrix.englishteasher.data.storage.interfaces

import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.RegistrationResult
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.domain.models.UserDataReg

interface FirebaseStorage {

    suspend fun sendDataToAuthInFirebase(userLoginAndPassword: User): String?

    fun sendEmailForRecoveryPassword(userEmail: User)

    fun sendSecretCodeForRecoveryPassword(firebaseSecretCode: FirebaseSecretCode)

    fun sendUserDataToRegInFirebase(userRegData: User) : RegistrationResult
}