package com.github.oneotrix.englishteasher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.oneotrix.englishteasher.databinding.ActivityMainBinding
import com.github.oneotrix.englishteasher.presentation.SplashScreenFragment

class MainActivity : AppCompatActivity() {

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
}