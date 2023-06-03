package com.github.oneotrix.englishteasher.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.usecase.SaveNewPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.viewmodel.RecoveryPasswordThirdVM

class RecoveryPasswordThirdVMFactory(userDatabase: AppDatabase) : ViewModelProvider.Factory {

    private val userRepository = UserRepositoryImpl(userDatabase)
    private val saveNewPasswordUseCase = SaveNewPasswordUseCase(userRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecoveryPasswordThirdVM(saveNewPasswordUseCase) as T
    }
}