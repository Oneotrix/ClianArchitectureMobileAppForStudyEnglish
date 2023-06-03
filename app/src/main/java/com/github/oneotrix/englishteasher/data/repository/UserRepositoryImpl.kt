package com.github.oneotrix.englishteasher.data.repository

import com.github.oneotrix.englishteasher.data.storage.FireBaseUserStorage
import com.github.oneotrix.englishteasher.data.storage.RoomUserStorage
import com.github.oneotrix.englishteasher.data.storage.interfaces.FirebaseStorage
import com.github.oneotrix.englishteasher.data.storage.interfaces.RoomStorage
import com.github.oneotrix.englishteasher.data.storage.models.FirebaseSecretCode
import com.github.oneotrix.englishteasher.data.storage.models.User
import com.github.oneotrix.englishteasher.data.storage.models.UserPassword
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.*
import com.github.oneotrix.englishteasher.domain.repository.UserRepository


class UserRepositoryImpl(private val roomStorage: RoomStorage = RoomUserStorage(),
                         private val firebaseStorage: FirebaseStorage = FireBaseUserStorage()
) : UserRepository {

    override fun sendDataToAuthInFirebase(userLoginAndPassword: UserLoginAndPassword) {
        val user = User(login = userLoginAndPassword.login,
                        password = userLoginAndPassword.password,
                        email = "")
        firebaseStorage?.sendDataToAuthInFirebase(user)
    }

    override fun saveUserDataInLocalDatabase(userLoginAndPassword: UserLoginAndPassword) {
        val user = User(login = userLoginAndPassword.login,
                        password = userLoginAndPassword.password,
                        email = "")
        roomStorage?.saveUserData(user)
    }

    override fun sendUserDataToRegInFirebase(userDataReg: UserDataReg) : RegistrationResult {
        val user = User(login = userDataReg.login,
                        password = userDataReg.password,
                        email = userDataReg.email)
        val result = firebaseStorage.sendUserDataToRegInFirebase(userRegData = user)
        return RegistrationResult(result.result, result.description)
    }

    override fun sendEmailForRecoveryPassword(userEmail: UserEmail) {
        val user = User(login = "",
                        password = "",
                        email = userEmail.email)
        firebaseStorage?.sendEmailForRecoveryPassword(user)
    }

    override fun sendSecretCodeForRecoveryPassword(secretCode: SecretCode) {
        val firebaseSecretCode = FirebaseSecretCode(code = secretCode.secretCode)
        firebaseStorage?.sendSecretCodeForRecoveryPassword(firebaseSecretCode)
    }

    override fun saveNewPassword(password: Password) {
        val userPassword = UserPassword(password = password.password)
        roomStorage?.saveNewPassword(userPassword)
    }

    override fun getUserDataForAuthFromDataBase(userDatabase: AppDatabase) : UserLoginAndPassword {
        return roomStorage.selectAllUserData(userDatabase)
    }

}