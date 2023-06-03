package com.github.oneotrix.englishteasher.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserDataToFirebaseForRegUseCase
import com.github.oneotrix.englishteasher.presentation.viewmodel.RegistrationViewModule

class RegistrationViewModuleFactory(userDatabase: AppDatabase) : ViewModelProvider.Factory {

    private val userRepository = UserRepositoryImpl(userDatabase)

    private val sendUserDataToFirebaseForRegUseCase
            = SendUserDataToFirebaseForRegUseCase(userRepository = userRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModule(sendUserDataToFirebaseForRegUseCase) as T
    }
}