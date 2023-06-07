package com.github.oneotrix.englishteasher.data.repository

import com.github.oneotrix.englishteasher.data.storage.FireBaseAuthAuthStorage
import com.github.oneotrix.englishteasher.data.storage.RoomUserStorage
import com.github.oneotrix.englishteasher.data.storage.interfaces.FirebaseAuthStorage
import com.github.oneotrix.englishteasher.data.storage.interfaces.RoomStorage
import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.data.storage.models.UserPassword
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.*
import com.github.oneotrix.englishteasher.domain.repository.UserRepository


class UserRepositoryImpl(userDatabase: AppDatabase) : UserRepository {

    private val roomStorage: RoomStorage = RoomUserStorage(userDatabase)
    private val firebaseAuthStorage: FirebaseAuthStorage = FireBaseAuthAuthStorage()
    override suspend fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword) : String? {
        val user = User(email = userLoginAndPassword.login,
                        password = userLoginAndPassword.password,
                        login = "")
        val result = firebaseAuthStorage.sendDataToAuthInFirebase(user)

        return result
    }

    override fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword) {
        val user = User(email = userLoginAndPassword.login,
                        password = userLoginAndPassword.password,
                        login = "")
        roomStorage?.saveUserData(user)
    }

    override suspend fun sendUserDataToRegInFirebase(userDataReg: UserDataReg) : String? {
        val user = User(login = userDataReg.login,
                        password = userDataReg.password,
                        email = userDataReg.email)
        val result = firebaseAuthStorage.sendUserDataToRegInFirebase(userRegData = user)
        return result
    }

    override fun sendEmailForRecoveryPassword(userEmail: UserEmail) {
        val user = User(login = "",
                        password = "",
                        email = userEmail.email)
        firebaseAuthStorage?.sendEmailForRecoveryPassword(user)
    }

    override fun sendSecretCodeForRecoveryPassword(secretCode: SecretCode) {
        val firebaseSecretCode = FirebaseSecretCode(code = secretCode.secretCode)
        firebaseAuthStorage?.sendSecretCodeForRecoveryPassword(firebaseSecretCode)
    }

    override fun saveNewPassword(password: Password) {
        val userPassword = UserPassword(password = password.password)
        roomStorage?.saveNewPassword(userPassword)
    }

    override fun getUserDataForAuthFromDataBase() : UserLoginAndPassword? {
        return roomStorage.selectAllUserData()
    }

}