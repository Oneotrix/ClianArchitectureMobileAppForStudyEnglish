package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.data.storage.room.user.UserDAO
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase
import com.github.oneotrix.englishteasher.presentation.models.UserEmailAndPassword
import kotlinx.coroutines.runBlocking

private const val TAG = "SignInViewModel"

//from keyboard
//from db

class SignInViewModel(
    private val saveUserLoginAndPasswordUseCase: SaveUserLoginAndPasswordUseCase,
    private val sendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase: SendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase,
    private val sendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase: SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase
) : ViewModel() {

    private lateinit var userAuthData: UserLoginAndPassword

    private val mLiveData: MutableLiveData<UserEmailAndPassword> = MutableLiveData()
    val liveData: LiveData<UserEmailAndPassword> = mLiveData
    init {
        Log.i(TAG, "SignInViewModel is created")
    }

    fun signIn(login: String = "",
               password: String = "",
               userDB: AppDatabase? = null) {

        if(userDB == null) {
            signInKeyboard(login, password)
        } else {
             sendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase.execute(userDB)
        }
    }

    private fun signInKeyboard(login: String, password: String) {
        val userData = UserLoginAndPassword(login = login, password = password)

        sendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase.execute(userLoginAndPassword = userData)
        saveUserLoginAndPasswordUseCase.execute(userData = userData)
    }



    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "SignInViewModel is cleared")
    }
}