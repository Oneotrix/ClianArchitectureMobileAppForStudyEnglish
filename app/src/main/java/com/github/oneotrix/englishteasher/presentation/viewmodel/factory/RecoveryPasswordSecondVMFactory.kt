package com.github.oneotrix.englishteasher.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.domain.usecase.SendSecretCodeForRecPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.viewmodel.RecoveryPasswordSecondVM

class RecoveryPasswordSecondVMFactory : ViewModelProvider.Factory {

    private val userRepository = UserRepositoryImpl()
    private val sendSecretCodeForRecPasswordUseCase
            = SendSecretCodeForRecPasswordUseCase(userRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecoveryPasswordSecondVM(sendSecretCodeForRecPasswordUseCase) as T
    }
}