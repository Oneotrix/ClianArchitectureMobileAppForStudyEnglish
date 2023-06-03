package com.github.oneotrix.englishteasher.data.storage

import android.util.Log
import com.github.oneotrix.englishteasher.data.storage.interfaces.RoomStorage
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.data.storage.models.UserPassword
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.data.storage.room.user.UserDAO
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import kotlinx.coroutines.runBlocking


private const val ROOM_TAG = "Room"

class RoomUserStorage : RoomStorage {
    override fun saveUserData(userDataReg: User) {



        Log.i(
            ROOM_TAG, "save to sqllite user data to reg \n" +
                    "login : ${userDataReg.login} \n" +
                    "password: ${userDataReg.password}")
    }

    override fun saveNewPassword(password: UserPassword) {
        Log.i(
            ROOM_TAG, "save new user password in sqlite \n" +
                "password : ${password.password}")
    }

    override fun selectAllUserData(userDatabase: AppDatabase): UserLoginAndPassword {
        val userDAO = userDatabase.userDAO()
        return selectUserFromDB(userDAO)
    }

    private fun selectUserFromDB(userDao: UserDAO) : UserLoginAndPassword  = runBlocking {
        val user = userDao.getUser()
        return@runBlocking UserLoginAndPassword(user[0].email, user[0].password)
    }


}