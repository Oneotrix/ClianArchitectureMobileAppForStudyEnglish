package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.repository.UserRepository
import com.github.oneotrix.englishteasher.domain.results.AuthResult

class SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase(private val userRepository: UserRepository) {

    suspend fun execute() : AuthResult {
        val userLoginAndPassword = userRepository.getUserDataForAuthFromDataBase()

        if (userLoginAndPassword != null){
            val answer = userRepository.sendDataToAuthInFirebase(userLoginAndPassword)

            return if (answer == null) {
                AuthResult.Success
            } else {
                AuthResult.Error(answer)
            }
        }

        return AuthResult.Error("No user data in database")
    }
}