package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRegistrationBinding
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.usecase.SendUserDataToFirebaseForRegUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.navigator

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val userRepository = UserRepositoryImpl()
    private val sendUserDataToFirebaseForRegUseCase
        = SendUserDataToFirebaseForRegUseCase(userRepository = userRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            navigator().goBack()
        }

        binding.registrationButton.setOnClickListener {
            val login = binding.login.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            val userDataReg = UserDataReg(login = login, email = email, password = password)
            sendUserDataToFirebaseForRegUseCase.execute(userDataReg)
        }

        return binding.root
    }


    companion object {
        fun newInstance() = RegistrationFragment()
    }
}