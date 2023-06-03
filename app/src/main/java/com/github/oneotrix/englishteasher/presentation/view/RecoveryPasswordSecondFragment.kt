package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRecoveryPasswordSecondBinding
import com.github.oneotrix.englishteasher.domain.models.SecretCode
import com.github.oneotrix.englishteasher.domain.usecase.SendSecretCodeForRecPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user.userDbEntity
import com.github.oneotrix.englishteasher.presentation.contracts.navigator
import com.github.oneotrix.englishteasher.presentation.viewmodel.RecoveryPasswordSecondVM
import com.github.oneotrix.englishteasher.presentation.viewmodel.factory.RecoveryPasswordSecondVMFactory

class RecoveryPasswordSecondFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryPasswordSecondBinding
    private val viewModel: RecoveryPasswordSecondVM by lazy {
        ViewModelProvider(this, RecoveryPasswordSecondVMFactory(userDbEntity().getUserDatabase()))
            .get(RecoveryPasswordSecondVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoveryPasswordSecondBinding.inflate(inflater, container, false)

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            binding.secredCodeInput.setText(it.secretCode)
        })

        binding.buttonNext.setOnClickListener {

            val code = binding.secredCodeInput.text.toString()
            viewModel.sendCode(code)
            //if true
            navigator().onRecoveryPasswordThird()
        }

        return binding.root
    }

    companion object {
        fun newInstance() = RecoveryPasswordSecondFragment()
    }
}