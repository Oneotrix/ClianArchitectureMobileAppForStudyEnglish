package com.github.oneotrix.englishteasher.domain.usecase

import android.util.Log
import com.github.oneotrix.englishteasher.domain.models.SaveUserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword

class SendUserLoginAndPasswordToFirebaseForAuthUseCase {

    fun execute(userLoginAndPassword: UserLoginAndPassword) {
        Log.i("SendToFirebase", "send to firebase user data to auth \n" +
                "login : ${userLoginAndPassword.login} \n" +
                "password: ${userLoginAndPassword.password}")
    }
}