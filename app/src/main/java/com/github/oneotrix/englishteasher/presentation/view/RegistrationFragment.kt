package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.github.oneotrix.englishteasher.data.repository.UserRepositoryImpl
import com.github.oneotrix.englishteasher.databinding.FragmentRegistrationBinding
import com.github.oneotrix.englishteasher.domain.models.UserDataReg
import com.github.oneotrix.englishteasher.domain.usecase.SendUserDataToFirebaseForRegUseCase
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user.userDbEntity
import com.github.oneotrix.englishteasher.presentation.contracts.navigator
import com.github.oneotrix.englishteasher.presentation.viewmodel.RegistrationViewModule
import com.github.oneotrix.englishteasher.presentation.viewmodel.factory.RegistrationViewModuleFactory
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private var errorMessage: String? = null

    private val viewModule : RegistrationViewModule by lazy {
        ViewModelProvider(this, RegistrationViewModuleFactory(userDbEntity().getUserDatabase()))
            .get(RegistrationViewModule::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        viewModule.liveData.observe(viewLifecycleOwner, Observer {
            binding.email.setText(it.email)
            binding.login.setText(it.login)
            binding.password.setText(it.password)
        }, )

        binding.backButton.setOnClickListener {
            navigator().goBack()
        }

        binding.registrationButton.setOnClickListener {
            val login = binding.login.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            sendDataToReg(
                login = login,
                email = email,
                password = password
            )

            if(errorMessage == null) {
                //go next
            } else {
                makeToast(message = errorMessage!!)
            }


        }

        return binding.root
    }


    private fun sendDataToReg(login: String, email: String, password: String){
        lifecycleScope.launch {
            errorMessage = viewModule.registration(email = email, login = login, password = password)

        }

    }

    private fun makeToast(message : String, length : Int = Toast.LENGTH_LONG) {
        Toast.makeText(this.context, message, length)
            .show()
    }


    companion object {
        fun newInstance() = RegistrationFragment()
    }
}