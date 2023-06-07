package com.github.oneotrix.englishteasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.oneotrix.englishteasher.data.storage.room.AppDatabase
import com.github.oneotrix.englishteasher.databinding.ActivityMainBinding
import com.github.oneotrix.englishteasher.presentation.contracts.Constants
import com.github.oneotrix.englishteasher.presentation.contracts.Navigator
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.RoomDatabaseCreator
import com.github.oneotrix.englishteasher.presentation.contracts.dbmanager.room.user.UserDatabaseProvider
import com.github.oneotrix.englishteasher.presentation.view.*

class MainActivity : AppCompatActivity(),
        Navigator,
        RoomDatabaseCreator,
        UserDatabaseProvider

{

    private lateinit var binding: ActivityMainBinding
    private lateinit var userDB : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        userDB = createUserDatabase(applicationContext)

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
        launchFragment(SignInFragment.newInstance())
       // launchFragment(fragment = RegistrationFragment.newInstance())

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

    override fun onMenu() {
        launchFragment(fragment = MenuFragment.newInstance())
    }

    private fun launchFragment(fragment: Fragment) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.fragmentContainer.id, fragment)
                .commit()
    }

    override fun getUserDatabase(): AppDatabase {
        return userDB
    }
}