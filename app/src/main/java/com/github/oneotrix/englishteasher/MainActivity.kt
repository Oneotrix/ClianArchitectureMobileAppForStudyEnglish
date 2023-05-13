package com.github.oneotrix.englishteasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.databinding.ActivityMainBinding
import com.github.oneotrix.englishteasher.presentation.*
import com.github.oneotrix.englishteasher.presentation.contracts.Navigator

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            val fragment = SplashScreenFragment.newInstance()

            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, fragment)
                .commit()
        }
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onSignIn() {
       // launchFragment(SignInFragment.newInstance())
        launchFragment(fragment = RegistrationFragment.newInstance())

    }

    override fun onRegistration() {
        launchFragment(fragment = RegistrationFragment.newInstance())
    }

    override fun onRecoveryPasswordFirst() {
        launchFragment(fragment = RecoveryPasswordFirstFragment.newInstance())
    }

    override fun onRecoveryPasswordSecond() {
        launchFragment(fragment = RecoveryPasswordSecondFragment.newInstance())
    }

    override fun onRecoveryPasswordThird() {
        launchFragment(fragment = RecoveryPasswordThirdFragment.newInstance())
    }

    private fun launchFragment(fragment: Fragment) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.fragmentContainer.id, fragment)
                .commit()
    }
}