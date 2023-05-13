package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.domain.models.Password
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SaveNewPasswordUseCase(private val userRepository: UserRepository) {

    fun execute(password: Password) {
        userRepository.saveNewPassword(password)
    }
}