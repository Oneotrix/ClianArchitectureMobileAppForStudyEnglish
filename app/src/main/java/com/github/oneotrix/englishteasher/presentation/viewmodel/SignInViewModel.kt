package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthUseCase
import com.github.oneotrix.englishteasher.presentation.models.UserEmailAndPassword

private const val TAG = "SignInViewModel"

class SignInViewModel(
    private val saveUserLoginAndPasswordUseCase: SaveUserLoginAndPasswordUseCase,
    private val sendUserLoginAndPasswordToFirebaseForAuthUseCase: SendUserLoginAndPasswordToFirebaseForAuthUseCase
) : ViewModel() {

    private val mLiveData: MutableLiveData<UserEmailAndPassword> = MutableLiveData()
    val liveData: LiveData<UserEmailAndPassword> = mLiveData

    init {
        Log.i(TAG, "SignInViewModel is created")
    }

    fun signIn(login: String, password: String) {
        val userData = UserLoginAndPassword(login = login, password = password)

        sendUserLoginAndPasswordToFirebaseForAuthUseCase.execute(userLoginAndPassword = userData)
        saveUserLoginAndPasswordUseCase.execute(userData = userData)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "SignInViewModel is cleared")
    }
}