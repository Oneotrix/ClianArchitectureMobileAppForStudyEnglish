package com.github.oneotrix.englishteasher.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.oneotrix.englishteasher.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)

        //to personal account fragment
        binding.personIcon.setOnClickListener {

        }

        //to dictionary fragment
        binding.bookIcon.setOnClickListener {

        }

        //to SignIn fragment
        binding.logoutIcon.setOnClickListener {

        }

        //to grammar module fragment
        binding.grammarModule.setOnClickListener {

        }

        //to time module fragment
        binding.timeModule.setOnClickListener {

        }

        //to model verbs module fragment
        binding.modalVerbsModule.setOnClickListener {

        }

        //to idioms fragment
        binding.idiomsModule.setOnClickListener {

        }

        //to words fragment
        binding.wordsModule.setOnClickListener {

        }


        return binding.root
    }

    companion object {
        fun newInstance() = MenuFragment()
    }
}