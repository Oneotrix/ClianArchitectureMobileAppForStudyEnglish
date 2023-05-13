package com.github.oneotrix.englishteasher.data.storage

import android.util.Log
import com.github.oneotrix.englishteasher.data.storage.interfaces.SQLiteStorage
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.data.storage.models.UserPassword


private const val SQLITE_TAG = "SQlite"

class SqliteUserStorage : SQLiteStorage {
    override fun saveUserData(userDataReg: User) {
        Log.i(
            SQLITE_TAG, "save to sqllite user data to reg \n" +
                    "login : ${userDataReg.login} \n" +
                    "password: ${userDataReg.password}")
    }

    override fun saveNewPassword(password: UserPassword) {
        Log.i(
            SQLITE_TAG, "save new user password in sqlite \n" +
                "password : ${password.password}")
    }

}