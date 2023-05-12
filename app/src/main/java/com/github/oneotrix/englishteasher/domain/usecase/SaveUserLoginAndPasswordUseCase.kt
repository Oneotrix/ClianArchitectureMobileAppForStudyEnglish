package com.github.oneotrix.englishteasher.domain.usecase

import android.util.Log
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SaveUserLoginAndPasswordUseCase(private val userRepository: UserRepository) {

    fun execute(userData: UserLoginAndPassword) {
        userRepository.saveUserDataInLocalDatabase(userData)
    }

}