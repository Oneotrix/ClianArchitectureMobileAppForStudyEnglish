package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.oneotrix.englishteasher.databinding.FragmentSignInBinding
import com.github.oneotrix.englishteasher.presentation.contracts.navigator
import com.github.oneotrix.englishteasher.presentation.viewmodel.SignInViewModel
import com.github.oneotrix.englishteasher.presentation.viewmodel.factory.SignInViewModelFactory

class SignInFragment: Fragment() {


    private val viewModel : SignInViewModel by lazy {
        ViewModelProvider(this, SignInViewModelFactory()).get(SignInViewModel::class.java)
    }
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            binding.loginEmailField.setText(it.email)
            binding.passwordField.setText(it.password)
        })

        binding.signIn.setOnClickListener {
            val login = binding.loginEmailField.text.toString()
            val password = binding.passwordField.text.toString()

            viewModel.signIn(login = login, password = password)
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