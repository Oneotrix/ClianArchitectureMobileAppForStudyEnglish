package com.github.oneotrix.englishteasher.data.storage.interfaces

import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.data.storage.models.UserPassword
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword

interface RoomStorage {

    fun saveUserData(userDataReg: User)

    fun saveNewPassword(password: UserPassword)

    fun selectAllUserData(): UserLoginAndPassword?

}