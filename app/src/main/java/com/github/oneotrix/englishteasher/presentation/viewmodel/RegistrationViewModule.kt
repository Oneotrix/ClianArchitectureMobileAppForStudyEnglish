package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
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

    fun registration(email : String, login: String, password: String ) {
        val userRegDate = UserDataReg(email = email, login = login, password = password)
        sendUserDataToFirebaseForRegUseCase.execute(userRegDate)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "$TAG is cleared")
    }
}