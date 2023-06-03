package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRecoveryPasswordFirstBinding
import com.github.oneotrix.englishteasher.domain.models.UserEmail
import com.github.oneotrix.englishteasher.domain.usecase.SendEmailForRecoveryPasswordUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user.userDbEntity
import com.github.oneotrix.englishteasher.presentation.contracts.navigator
import com.github.oneotrix.englishteasher.presentation.viewmodel.RecoveryPasswordFirstViewModule
import com.github.oneotrix.englishteasher.presentation.viewmodel.factory.RecoveryPasswordFirstVMFactory

class RecoveryPasswordFirstFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryPasswordFirstBinding
    private val viewModule: RecoveryPasswordFirstViewModule by lazy {
        ViewModelProvider(this, RecoveryPasswordFirstVMFactory(userDbEntity().getUserDatabase()))
            .get(RecoveryPasswordFirstViewModule::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecoveryPasswordFirstBinding.inflate(inflater, container, false)

        viewModule.liveDate.observe(viewLifecycleOwner, Observer {
            binding.emailRecoveryPassword.setText(it.email)
        })

        binding.buttonRecoveryPasswordFirst.setOnClickListener {
            val email = binding.emailRecoveryPassword.text.toString()
            val userEmail = UserEmail(email = email)

            viewModule.send(userEmail.email)
            //if true go to second screen recovery
            navigator().onRecoveryPasswordSecond()

        }

        return binding.root
    }

    companion object {
        fun newInstance() = RecoveryPasswordFirstFragment()
    }
}