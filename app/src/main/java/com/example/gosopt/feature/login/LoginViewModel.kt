package com.example.gosopt.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _id = MutableLiveData<String>()
    val id : LiveData<String> get() = _id

    private val _pw = MutableLiveData<String>()
    val pw : LiveData<String> get() = _pw

    fun login() {

    }

}