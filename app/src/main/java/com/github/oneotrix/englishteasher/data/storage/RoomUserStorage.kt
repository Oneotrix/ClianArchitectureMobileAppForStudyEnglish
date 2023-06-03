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

class RoomUserStorage(userDatabase: AppDatabase) : RoomStorage {

    private val userDB = userDatabase
    private val userDao: UserDAO by lazy {
        userDB.userDAO()
    }

    override fun saveUserData(userDataReg: User) {

        insertUser()
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

    override fun selectAllUserData(): UserLoginAndPassword? {
        return selectUserFromDB()
    }

    private fun selectUserFromDB() : UserLoginAndPassword?  = runBlocking {
        val user = userDao.getUser()
        try {
            return@runBlocking UserLoginAndPassword(user[0].email, user[0].password)
        } catch (e: IndexOutOfBoundsException) {
            return@runBlocking null
        }
    }

    private fun insertUser() = runBlocking {
        userDao.insertEmailAndPassword(User(login = "log", email = "em", password = "pass"))
    }


}