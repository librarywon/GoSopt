package com.example.gosopt.feature.signup

import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    fun checkId(id: String): Boolean {
        return id.length in 6..10
    }

    fun checkPw(pw: String): Boolean {
        return pw.length in 8..12
    }
}