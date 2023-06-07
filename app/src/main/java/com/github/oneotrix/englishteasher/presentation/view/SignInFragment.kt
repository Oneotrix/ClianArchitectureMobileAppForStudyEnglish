package com.github.oneotrix.englishteasher.presentation.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.oneotrix.englishteasher.R
import com.github.oneotrix.englishteasher.databinding.FragmentSignInBinding
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user.userDbEntity
import com.github.oneotrix.englishteasher.presentation.contracts.navigator
import com.github.oneotrix.englishteasher.presentation.viewmodel.SignInViewModel
import com.github.oneotrix.englishteasher.presentation.viewmodel.factory.SignInViewModelFactory
import kotlinx.coroutines.launch

private const val FRAGMENT_TAG = "SignInFragment"

class SignInFragment: Fragment() {


    private val viewModel : SignInViewModel by lazy {
        ViewModelProvider(this, SignInViewModelFactory(userDbEntity().getUserDatabase()))
            .get(SignInViewModel::class.java)
    }
    private lateinit var binding: FragmentSignInBinding
    private var isAuthSuccess: Boolean = false
    private var errorMessage: String? = null


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
            val email = binding.loginEmailField.text.toString()
            val password = binding.passwordField.text.toString()

            lifecycleScope.launch {
                errorMessage = viewModel.signInKeyboard(lifecycle = lifecycleScope,email = email, password = password)
            }

            if(errorMessage == null) {
              isAuthSuccess = true
            }

            if(!isAuthSuccess) {
                makeToast(errorMessage!!, Toast.LENGTH_LONG)
                setEmailFieldHint()
                clearEmailField()
                clearPasswordField()
            } else {
                navigator().onMenu()
            }
        }

        binding.RegistrationTextView.setOnClickListener {
            navigator().onRegistration()
        }

        binding.forgotPasswordTextView.setOnClickListener {
            navigator().onRecoveryPasswordFirst()
        }

        return  binding.root
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            errorMessage = viewModel.signInFromDatabase()
        }

    }

    override fun onResume() {
        super.onResume()
        if(errorMessage == null) {
            navigator().onMenu()
        }
    }


    private fun makeToast(message : String, length : Int) {
        Toast.makeText(this.context, message, length)
            .show()
    }

    private fun setEmailFieldHint() {
        binding.loginEmailField.setHintTextColor(Color.RED)
       // binding.loginEmailField.backgroundTintList = context?.resources?.getColorStateList()
    }

    private fun clearEmailField() {
        binding.loginEmailField.text.clear()
    }

    private fun clearPasswordField() {
        binding.passwordField.text.clear()
    }


    companion object {
        fun newInstance() = SignInFragment()
    }
}