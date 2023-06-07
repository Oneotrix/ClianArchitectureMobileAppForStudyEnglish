package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.results.AuthResult
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase
import com.github.oneotrix.englishteasher.presentation.models.UserEmailAndPassword

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

    suspend fun signInKeyboard(lifecycle: LifecycleCoroutineScope, email: String, password: String) : String?{
        val userData = UserLoginAndPassword(login = email, password = password)


        val answer = sendUserLoginAndPasswordToFirebaseForAuthFromKeyboardUseCase.execute(userLoginAndPassword = userData)

        return when(answer) {
            is AuthResult.Success -> {
                saveUserLoginAndPasswordUseCase.execute(userData = userData)
                null
            }

            is AuthResult.Error -> {
                answer.message
            }
        }
    }

    suspend fun signInFromDatabase() : String? {
        saveUserLoginAndPasswordUseCase.execute(userData = UserLoginAndPassword("loas", "123"))
        val answer = sendUserLoginAndPasswordToFirebaseForAuthFromDBUseCase.execute()

         return when(answer) {
             is AuthResult.Success -> {
                 val userData = UserLoginAndPassword(
                     login = mLiveData.value!!.email,
                     password = mLiveData.value!!.password)

                 saveUserLoginAndPasswordUseCase.execute(userData = userData)
                 null
             }

             is AuthResult.Error -> {
                 answer.message
             }
         }
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "SignInViewModel is cleared")
    }
}