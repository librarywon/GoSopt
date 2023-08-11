package com.example.gosopt.feature.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gosopt.R
import com.example.gosopt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}