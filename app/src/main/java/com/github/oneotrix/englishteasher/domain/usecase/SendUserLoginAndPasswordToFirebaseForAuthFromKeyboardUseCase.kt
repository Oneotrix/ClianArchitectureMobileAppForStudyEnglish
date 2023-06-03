package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.repository.UserRepository
import com.github.oneotrix.englishteasher.domain.results.AuthResult

class SendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase(private val userRepository: UserRepository) {
    suspend fun execute(userLoginAndPassword: UserLoginAndPassword) : AuthResult {
        val answer = userRepository.sendDataToAuthInFirebase(userLoginAndPassword)

        return if (answer == null) {
            AuthResult.Success
        } else {
            AuthResult.Error(answer)
        }
    }
}