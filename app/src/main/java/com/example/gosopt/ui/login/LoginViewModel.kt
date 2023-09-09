package com.example.gosopt.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gosopt.data.model.User

class LoginViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User> get() = _userInfo

    fun setUserInfo(id: String, pw: String, name: String, hobby: String) {
        _userInfo.value = User(id, pw, name, hobby)
    }
}