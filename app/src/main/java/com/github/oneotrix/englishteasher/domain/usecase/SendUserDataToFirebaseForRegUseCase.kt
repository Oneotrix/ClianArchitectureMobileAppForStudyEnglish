package com.github.oneotrix.englishteasher.domain.usecase

import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.repository.UserRepository

class SendUserDataToFirebaseForRegUseCase(private val userRepository: UserRepository) {

    fun execute(userRegData: UserDataReg ) {
        userRepository.sendUserDataToRegInFirebase(userDataReg = userRegData)
    }
}