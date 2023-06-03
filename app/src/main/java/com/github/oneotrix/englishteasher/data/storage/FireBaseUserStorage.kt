package com.github.oneotrix.englishteasher.data.storage

import android.util.Log
import com.github.oneotrix.englishteasher.data.storage.firebase.FirebaseObject
import com.github.oneotrix.englishteasher.data.storage.interfaces.FirebaseStorage
import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.RegistrationResult
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


private const val FIREBASE_TAG = "Firebase"

class FireBaseUserStorage : FirebaseStorage {

    private lateinit var firebaseAuth: FirebaseAuth

    override suspend fun sendDataToAuthInFirebase(userLoginAndPassword: User): String? {
        var errorMessage: String? = null
        firebaseAuth = FirebaseAuth.getInstance()

        try {
            firebaseAuth.signInWithEmailAndPassword(
                userLoginAndPassword.email,
                userLoginAndPassword.password
            ).addOnCompleteListener {
                if(it.isSuccessful) {
                    errorMessage = null
                } else {
                    errorMessage = it.exception?.message
                }
            }.await()
        } catch (e: Exception) {
            e.message?.let { Log.d(FIREBASE_TAG, it) }
            Log.d(FIREBASE_TAG,"Catch an error")
            return e.message
        }

        return errorMessage
    }

    override fun sendEmailForRecoveryPassword(userEmail: User) {
        Log.i(FIREBASE_TAG, "send to firebase user email for recovery password \n" +
                "email : ${userEmail.email}")
    }

    override fun sendSecretCodeForRecoveryPassword(firebaseSecretCode: FirebaseSecretCode) {
        Log.i(FIREBASE_TAG, "send to firebase user secret code for recovery password \n" +
                "email : ${firebaseSecretCode.code}")
    }

    override fun sendUserDataToRegInFirebase(userRegData: User) : RegistrationResult {
        val registrationResult = FirebaseObject.createUser(user = userRegData)
        return registrationResult
    }
}