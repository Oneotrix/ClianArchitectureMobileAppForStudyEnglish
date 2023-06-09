package com.github.oneotrix.englishteasher.data.storage.interfaces

import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.User

interface FirebaseAuthStorage {

    suspend fun sendDataToAuthInFirebase(userLoginAndPassword: User): String?

    fun sendEmailForRecoveryPassword(userEmail: User)

    fun sendSecretCodeForRecoveryPassword(firebaseSecretCode: FirebaseSecretCode)

    suspend fun sendUserDataToRegInFirebase(userRegData: User) : String?
}