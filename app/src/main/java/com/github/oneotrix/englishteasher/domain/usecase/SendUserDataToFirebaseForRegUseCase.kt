package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.domain.models.RegistrationResult
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.repository.UserRepository
import com.github.oneotrix.englishteasher.domain.results.RegResult

class SendUserDataToFirebaseForRegUseCase(private val userRepository: UserRepository) {

    suspend fun execute(userRegData: UserDataReg ) : RegResult {
        val answer = userRepository.sendUserDataToRegInFirebase(userDataReg = userRegData)

        return if(answer == null) {
            RegResult.Success
        } else {
            RegResult.Error(answer)
        }
    }
}