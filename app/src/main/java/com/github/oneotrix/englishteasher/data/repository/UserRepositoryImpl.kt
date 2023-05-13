package com.github.oneotrix.englishteasher.data.repository

import android.util.Log
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

private const val FIREBASE_TAG = "Firebase"
private const val SQLITE_TAG = "SQlite"

class UserRepositoryImpl : UserRepository {

    override fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword) {
        Log.i(FIREBASE_TAG, "send to firebase user data to auth \n" +
                "login : ${userLoginAndPassword.login} \n" +
                "password: ${userLoginAndPassword.password}")
    }

    override fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword) {
        Log.i(SQLITE_TAG, "save user data to local database \n" +
                "login : ${userLoginAndPassword.login} \n" +
                "password: ${userLoginAndPassword.password}")
    }

    override fun sendUserDataToRegInFirebase(userDataReg: UserDataReg) {
        Log.i(FIREBASE_TAG, "send to firebase user data to reg \n" +
                "login : ${userDataReg.login} \n" +
                "email : ${userDataReg.email} \n" +
                "password: ${userDataReg.password}")
    }
}