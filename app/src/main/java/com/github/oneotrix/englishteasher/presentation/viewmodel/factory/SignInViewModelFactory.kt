package com.github.oneotrix.englishteasher.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthUseCase
import com.github.oneotrix.englishteasher.presentation.viewmodel.SignInViewModel

class SignInViewModelFactory : ViewModelProvider.Factory {

    private val userRepository = UserRepositoryImpl()

    private val saveUserLoginAndPasswordUseCase
            = SaveUserLoginAndPasswordUseCase(userRepository = userRepository)

    private val sendUserLoginAndPasswordToFirebaseForAuthUseCase
            = SendUserLoginAndPasswordToFirebaseForAuthUseCase(userRepository = userRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(saveUserLoginAndPasswordUseCase = saveUserLoginAndPasswordUseCase,
                               sendUserLoginAndPasswordToFirebaseForAuthUseCase =  sendUserLoginAndPasswordToFirebaseForAuthUseCase) as T
    }
}