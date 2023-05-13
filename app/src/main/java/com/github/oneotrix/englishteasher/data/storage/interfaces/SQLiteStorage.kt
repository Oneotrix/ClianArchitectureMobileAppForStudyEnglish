package com.github.oneotrix.englishteasher.data.storage.interfaces

import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.data.storage.models.UserPassword

interface SQLiteStorage {

    fun saveUserData(userDataReg: User)

    fun saveNewPassword(password: UserPassword)

}