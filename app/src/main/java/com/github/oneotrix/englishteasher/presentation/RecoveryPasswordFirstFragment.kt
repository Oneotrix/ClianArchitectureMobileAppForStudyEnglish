package com.github.oneotrix.englishteasher.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRecoveryPasswordFirstBinding
import com.github.oneotrix.englishteasher.domain.models.UserEmail
import com.github.oneotrix.englishteasher.domain.usecase.SendEmailForRecoveryPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.navigator

class RecoveryPasswordFirstFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryPasswordFirstBinding
    private val userRepository = UserRepositoryImpl()
    private val sendEmailForRecoveryPasswordUseCase
        =  SendEmailForRecoveryPasswordUseCase(userRepository = userRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoveryPasswordFirstBinding.inflate(inflater, container, false)

        binding.buttonRecoveryPasswordFirst.setOnClickListener {
            val email = binding.emailRecoveryPassword.text.toString()
            val userEmail = UserEmail(email = email)
            sendEmailForRecoveryPasswordUseCase.execute(userEmail)

            //if true go to second screen recovery
            navigator().onRecoveryPasswordSecond()

        }

        return binding.root
    }

    companion object {
        fun newInstance() = RecoveryPasswordFirstFragment()
    }
}