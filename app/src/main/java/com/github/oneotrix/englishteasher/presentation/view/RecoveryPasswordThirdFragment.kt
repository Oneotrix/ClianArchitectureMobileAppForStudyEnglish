package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRecoveryPasswordThirdBinding
import com.github.oneotrix.englishteasher.domain.models.Password
import com.github.oneotrix.englishteasher.domain.usecase.SaveNewPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user.userDbEntity
import com.github.oneotrix.englishteasher.presentation.viewmodel.RecoveryPasswordThirdVM
import com.github.oneotrix.englishteasher.presentation.viewmodel.factory.RecoveryPasswordThirdVMFactory

class RecoveryPasswordThirdFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryPasswordThirdBinding
    private val viewModule: RecoveryPasswordThirdVM by lazy {
        ViewModelProvider(this, RecoveryPasswordThirdVMFactory(userDbEntity().getUserDatabase()))
            .get(RecoveryPasswordThirdVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecoveryPasswordThirdBinding.inflate(inflater, container, false)

        viewModule.liveData.observe(viewLifecycleOwner, Observer {
            binding.passwordThirdRecoveryPassword.setText(it.password)
            binding.confirmPassword.setText(it.rPassword)
        })

        binding.button.setOnClickListener {
            val nPassword = binding.passwordThirdRecoveryPassword.text.toString()
            viewModule.save(nPassword)
        }

        return binding.root
    }

    companion object {
        fun newInstance() = RecoveryPasswordThirdFragment()
    }
}