package com.github.oneotrix.englishteasher.domain.usecase

import android.util.Log
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SendUserLoginAndPasswordToFirebaseForAuthUseCase(private val userRepository: UserRepository) {

    fun execute(userLoginAndPassword: UserLoginAndPassword) {

        userRepository.sendDataToAuthInFirebase(userLoginAndPassword)
    }
}