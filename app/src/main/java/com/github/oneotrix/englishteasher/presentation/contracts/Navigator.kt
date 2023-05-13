package com.github.oneotrix.englishteasher.presentation.contracts

import androidx.fragment.app.Fragment

fun Fragment.navigator() : Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun goBack()

    fun onSignIn()

    fun onRegistration()

}