package com.example.gosopt.feature.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosopt.R
import com.example.gosopt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    //by lazy를 사용하면 해당 변수가 사용될 때 초기화가 진행됩니다.
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}