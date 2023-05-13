package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.SecretCode
import com.github.oneotrix.englishteasher.domain.usecase.SendSecretCodeForRecPasswordUseCase


private const val TAG = "RecoveryPasswordSecondVM"

class RecoveryPasswordSecondVM(
    private val sendSecretCodeForRecPasswordUseCase: SendSecretCodeForRecPasswordUseCase
): ViewModel() {

    private val mLiveData: MutableLiveData<com.github.oneotrix.englishteasher.presentation.models.SecretCode>
            = MutableLiveData()

    val liveData : LiveData<com.github.oneotrix.englishteasher.presentation.models.SecretCode>
            = mLiveData

    init {
        Log.i(TAG, "$TAG is created")
    }

    fun sendCode(secretCode: String) {
        val code = SecretCode(secretCode)
        sendSecretCodeForRecPasswordUseCase.execute(code)
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "$TAG is cleared")
    }
}