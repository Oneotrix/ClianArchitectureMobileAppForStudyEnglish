package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.RegistrationResult
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.results.AuthResult
import com.github.oneotrix.englishteasher.domain.results.RegResult
import com.github.oneotrix.englishteasher.domain.usecase.SendUserDataToFirebaseForRegUseCase
import com.github.oneotrix.englishteasher.presentation.models.UserEmailAndPassword
import com.github.oneotrix.englishteasher.presentation.models.UserEmailPasswordLogin

private const val TAG = "RegistrationViewModel"

class RegistrationViewModule(
    private val sendUserDataToFirebaseForRegUseCase : SendUserDataToFirebaseForRegUseCase
) : ViewModel() {


    private val mLiveData: MutableLiveData<UserEmailPasswordLogin> = MutableLiveData()
    val liveData: LiveData<UserEmailPasswordLogin> = mLiveData

    init {
        Log.i(TAG, "$TAG is created")
    }

    suspend fun registration(email : String, login: String, password: String ) : String? {
        val userRegDate = UserDataReg(email = email, login = login, password = password)
        val result = sendUserDataToFirebaseForRegUseCase.execute(userRegDate)

        return when (result) {
            is RegResult.Success -> {
                return null
            }
            is RegResult.Error -> {
                result.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "$TAG is cleared")
    }
}