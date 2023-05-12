package com.github.oneotrix.englishteasher.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.databinding.FragmentSignInBinding
import com.github.oneotrix.englishteasher.domain.models.SaveUserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthUseCase

class SignInFragment: Fragment() {

    private val saveUserLoginAndPasswordUseCase = SaveUserLoginAndPasswordUseCase()
    private val sendUserLoginAndPasswordToFirebaseForAuthUseCase
                                = SendUserLoginAndPasswordToFirebaseForAuthUseCase()

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)


        binding.signIn.setOnClickListener {
            val login = binding.loginEmailField.text.toString()
            val password = binding.passwordField.text.toString()

            val userData = SaveUserLoginAndPassword(login = login, password = password)
            saveUserLoginAndPasswordUseCase.execute(userData = userData)

        }

        return  binding.root
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}