package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.domain.models.SecretCode
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SendSecretCodeForRecPasswordUseCase(private val userRepository: UserRepository) {

    fun execute(secretCode: SecretCode) {
        userRepository.sendSecretCodeForRecoveryPassword(secretCode = secretCode)
    }
}