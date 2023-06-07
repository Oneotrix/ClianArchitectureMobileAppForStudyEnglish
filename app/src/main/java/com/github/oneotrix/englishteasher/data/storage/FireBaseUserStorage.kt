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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


private const val FIREBASE_AUTH_TAG = "Firebase auth"
private const val FIREBASE_REG_TAG = "Firebase reg"
private const val FIREBASE_RESENT_MESSAGE_TAG = "Firebase resent message"


class FireBaseUserStorage : FirebaseStorage {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override suspend fun sendDataToAuthInFirebase(userLoginAndPassword: User): String?  {
        var errorMessage: String? = null

        try {
            firebaseAuth.signInWithEmailAndPassword(
                userLoginAndPassword.email,
                userLoginAndPassword.password
            ).addOnCompleteListener {
                errorMessage = if (it.isSuccessful) null
                               else it.exception?.message
                Log.i(FIREBASE_AUTH_TAG, "take msg" + errorMessage.toString())

            }.await()
        } catch (e: Exception){
            errorMessage = e.message
           Log.i(
               FIREBASE_AUTH_TAG, "catch EXP" + errorMessage.toString() +
                   "email : ${userLoginAndPassword.email}" +
                   "password : ${userLoginAndPassword.password}")
        }

        Log.i(FIREBASE_AUTH_TAG, "after " + errorMessage.toString())

        return errorMessage
    }

    override fun sendEmailForRecoveryPassword(userEmail: User) {

        firebaseAuth.sendPasswordResetEmail(userEmail.email)

        Log.i(
            FIREBASE_RESENT_MESSAGE_TAG, "send to firebase user email for recovery password \n" +
                "email : ${userEmail.email}")
    }

    override fun sendSecretCodeForRecoveryPassword(firebaseSecretCode: FirebaseSecretCode) {
        Log.i(
            FIREBASE_RESENT_MESSAGE_TAG, "send to firebase user secret code for recovery password \n" +
                "email : ${firebaseSecretCode.code}")
    }

    override suspend fun sendUserDataToRegInFirebase(userRegData: User): String? {

        var errorMessage: String? = null

        try {
            firebaseAuth.createUserWithEmailAndPassword(
                userRegData.email,
                userRegData.password
            ).addOnCompleteListener {
                errorMessage = if (it.isSuccessful) null
                               else it.exception?.message
                Log.i(FIREBASE_REG_TAG, "take msg" + errorMessage.toString())
            }.await()
        } catch (e: java.lang.Exception) {
            errorMessage = e.message
            Log.i(FIREBASE_REG_TAG, "take msg" + errorMessage.toString())
        }

        return errorMessage
    }
}