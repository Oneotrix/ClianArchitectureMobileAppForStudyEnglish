package com.github.oneotrix.englishteasher.data.storage.interfaces

import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.User
interface FirebaseStorage {

    fun sendDataToAuthInFirebase(userLoginAndPassword: User)

    fun sendEmailForRecoveryPassword(userEmail: User)

    fun sendSecretCodeForRecoveryPassword(firebaseSecretCode: FirebaseSecretCode)
}