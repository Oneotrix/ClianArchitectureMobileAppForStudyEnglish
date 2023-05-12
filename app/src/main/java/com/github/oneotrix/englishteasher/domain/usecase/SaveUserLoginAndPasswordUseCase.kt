package com.github.oneotrix.englishteasher.domain.usecase

import android.util.Log
import com.github.oneotrix.englishteasher.domain.models.SaveUserLoginAndPassword

class SaveUserLoginAndPasswordUseCase {

    fun execute(userData: SaveUserLoginAndPassword) {
        Log.i("SaveDATA", "${userData.login}, ${userData.password}")
    }

}