package com.github.oneotrix.englishteasher.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentSignInBinding
import com.github.oneotrix.englishteasher.domain.models.UserLoginAndPassword
import com.github.oneotrix.englishteasher.domain.usecase.SaveUserLoginAndPasswordUseCase
import com.github.oneotrix.englishteasher.domain.usecase.SendUserLoginAndPasswordToFirebaseForAuthUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.navigator

class SignInFragment: Fragment() {

    private val userRepository = UserRepositoryImpl()
    private val saveUserLoginAndPasswordUseCase
                 = SaveUserLoginAndPasswordUseCase(userRepository = userRepository)
    private val sendUserLoginAndPasswordToFirebaseForAuthUseCase
                 = SendUserLoginAndPasswordToFirebaseForAuthUseCase(userRepository = userRepository)

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

            val userData = UserLoginAndPassword(login = login, password = password)
            saveUserLoginAndPasswordUseCase.execute(userData = userData)

        }

        binding.RegistrationTextView.setOnClickListener {
            navigator().onRegistration()
        }

        binding.forgotPasswordTextView.setOnClickListener {
            navigator().onRecoveryPasswordFirst()
        }

        return  binding.root
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}