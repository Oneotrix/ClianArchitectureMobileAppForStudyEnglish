package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.domain.models.UserEmail
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SendEmailForRecoveryPasswordUseCase(private val userRepository: UserRepository) {

    fun execute(userEmail: UserEmail) {
            userRepository.sendEmailForRecoveryPassword(userEmail = userEmail)
    }
}