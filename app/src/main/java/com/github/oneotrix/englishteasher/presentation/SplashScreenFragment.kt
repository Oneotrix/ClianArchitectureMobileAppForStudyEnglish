package com.github.oneotrix.englishteasher.presentation

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.databinding.FragmentSplashScreenBinding
import com.github.oneotrix.englishteasher.presentation.contracts.navigator

class SplashScreenFragment: Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        val signInFragment = SignInFragment.newInstance()

        val timer = object : CountDownTimer(7000, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                navigator().onSignIn()
            }

        }
        timer.start()
        return binding.root
    }


    override fun onResume() {
        super.onResume()

    }

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

}