package com.github.oneotrix.englishteasher.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRecoveryPasswordSecondBinding
import com.github.oneotrix.englishteasher.domain.models.SecretCode
import com.github.oneotrix.englishteasher.domain.usecase.SendSecretCodeForRecPasswordUseCase

class RecoveryPasswordSecondFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryPasswordSecondBinding
    private val userRepository = UserRepositoryImpl()
    private val sendSecretCodeForRecPasswordUseCase
            = SendSecretCodeForRecPasswordUseCase(userRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoveryPasswordSecondBinding.inflate(inflater, container, false)

        binding.buttonNext.setOnClickListener {
            val code = binding.secredCodeInput.text.toString()
            val secretCode = SecretCode(secretCode = code)
            sendSecretCodeForRecPasswordUseCase.execute(secretCode)
        }

        return binding.root
    }

    companion object {
        fun newInstance() = RecoveryPasswordSecondFragment()
    }
}