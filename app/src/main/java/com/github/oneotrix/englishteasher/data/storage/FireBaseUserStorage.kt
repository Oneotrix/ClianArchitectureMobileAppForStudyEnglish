package com.github.oneotrix.englishteasher.data.storage

import android.util.Log
import com.github.oneotrix.englishteasher.data.storage.interfaces.FirebaseStorage
import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.User


private const val FIREBASE_TAG = "Firebase"

class FireBaseUserStorage : FirebaseStorage {

    override fun sendDataToAuthInFirebase(userLoginAndPassword: User) {
        Log.i(FIREBASE_TAG, "send to firebase user data to auth \n" +
                "login : ${userLoginAndPassword.login} \n" +
                "password: ${userLoginAndPassword.password}")
    }

    override fun sendEmailForRecoveryPassword(userEmail: User) {
        Log.i(FIREBASE_TAG, "send to firebase user email for recovery password \n" +
                "email : ${userEmail.email}")
    }

    override fun sendSecretCodeForRecoveryPassword(firebaseSecretCode: FirebaseSecretCode) {
        Log.i(FIREBASE_TAG, "send to firebase user secret code for recovery password \n" +
                "email : ${firebaseSecretCode.code}")
    }
}