package com.github.oneotrix.englishteasher.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.domain.usecase.SendEmailForRecoveryPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.viewmodel.RecoveryPasswordFirstViewModule

class RecoveryPasswordFirstVMFactory : ViewModelProvider.Factory {
    private val userRepository = UserRepositoryImpl()
    private val sendEmailForRecoveryPasswordUseCase
            =  SendEmailForRecoveryPasswordUseCase(userRepository = userRepository)


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecoveryPasswordFirstViewModule(sendEmailForRecoveryPasswordUseCase) as T
    }

}