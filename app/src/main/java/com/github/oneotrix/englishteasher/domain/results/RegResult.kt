package com.github.oneotrix.englishteasher.domain.results

sealed class RegResult {
    object Success : RegResult()
    data class Error(val message: String) : RegResult()
}