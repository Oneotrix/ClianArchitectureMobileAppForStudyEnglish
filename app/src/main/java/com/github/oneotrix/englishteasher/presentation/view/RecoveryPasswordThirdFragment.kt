package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRecoveryPasswordThirdBinding
import com.github.oneotrix.englishteasher.domain.models.Password
import com.github.oneotrix.englishteasher.domain.usecase.SaveNewPasswordUseCase

class RecoveryPasswordThirdFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryPasswordThirdBinding
    private val userRepository = UserRepositoryImpl()
    private val saveNewPasswordUseCase = SaveNewPasswordUseCase(userRepository)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecoveryPasswordThirdBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val nPassword = binding.passwordThirdRecoveryPassword.text.toString()
            val password = Password(nPassword)
            saveNewPasswordUseCase.execute(password)
        }

        return binding.root
    }

    companion object {
        fun newInstance() = RecoveryPasswordThirdFragment()
    }
}