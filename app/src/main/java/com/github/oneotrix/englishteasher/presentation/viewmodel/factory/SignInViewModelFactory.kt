package com.github.oneotrix.englishteasher.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase
import com.github.oneotrix.englishteasher.presentation.viewmodel.SignInViewModel

class SignInViewModelFactory : ViewModelProvider.Factory {

    private val userRepository = UserRepositoryImpl()

    private val saveUserLoginAndPasswordUseCase
            = SaveUserLoginAndPasswordUseCase(userRepository = userRepository)

    private val sendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase
            = SendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase(userRepository = userRepository)

    private val sendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase
    = SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase(userRepository = userRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(saveUserLoginAndPasswordUseCase = saveUserLoginAndPasswordUseCase,
                               sendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase = sendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase,
                               sendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase = sendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase)
                as T
    }
}