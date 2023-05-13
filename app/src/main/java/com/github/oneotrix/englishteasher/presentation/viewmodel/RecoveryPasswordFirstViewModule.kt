package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.UserEmail
import com.github.oneotrix.englishteasher.domain.usecase.SendEmailForRecoveryPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.models.UserEmailPasswordLogin

private const val TAG = "RecoveryPasswordFirstFragment"

class RecoveryPasswordFirstViewModule(
    private val sendEmailForRecoveryPasswordUseCase : SendEmailForRecoveryPasswordUseCase
) : ViewModel(){

    private val mLivaData: MutableLiveData<UserEmailPasswordLogin> = MutableLiveData()
    val liveDate: LiveData<UserEmailPasswordLogin> = mLivaData

    init {
        Log.i(TAG, "$TAG is created")
    }

    fun send(email: String) {
        val userEmail = UserEmail(email = email)
        sendEmailForRecoveryPasswordUseCase.execute(userEmail)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "$TAG is cleared")
    }
}