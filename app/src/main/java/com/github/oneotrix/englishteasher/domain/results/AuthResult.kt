package com.github.oneotrix.englishteasher.domain.results

sealed class AuthResult {
    object Success : AuthResult()
    data class Error(val message: String) : AuthResult()
}
