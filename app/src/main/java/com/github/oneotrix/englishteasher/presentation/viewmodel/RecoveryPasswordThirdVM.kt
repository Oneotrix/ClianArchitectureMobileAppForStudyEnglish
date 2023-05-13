package com.github.oneotrix.englishteasher.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.oneotrix.englishteasher.domain.models.Password
import com.github.oneotrix.englishteasher.domain.usecase.SaveNewPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.models.PasswordRepeatPassword
import com.github.oneotrix.englishteasher.presentation.models.UserEmailPasswordLogin

private const val TAG = "RecoveryPasswordThirdVM"

class RecoveryPasswordThirdVM(
    private val saveNewPasswordUseCase: SaveNewPasswordUseCase
) : ViewModel() {

    private val mLiveData: MutableLiveData<PasswordRepeatPassword> = MutableLiveData()
    val liveData: LiveData<PasswordRepeatPassword> = mLiveData

    init {
        Log.i(TAG, "$TAG created")
    }


    fun save(newPassword: String) {
        val password = Password(password = newPassword)
        saveNewPasswordUseCase.execute(password)
    }


    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "$TAG is cleared")
    }
}