package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase(private val userRepository: UserRepository) {

    fun execute(userDatabase: AppDatabase) {
        val userLoginAndPassword = userRepository.getUserDataForAuthFromDataBase(userDatabase)
        userRepository.sendDataToAuthInFirebase(userLoginAndPassword)
    }
}