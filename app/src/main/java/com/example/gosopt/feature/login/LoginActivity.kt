package com.example.gosopt.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosopt.databinding.ActivityLoginBinding
import com.example.gosopt.feature.signup.SignupActivity

class LoginActivity : AppCompatActivity() {
    //by lazy를 사용하면 해당 변수가 사용될 때 초기화가 진행됩니다.
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setOnClickListener()
    }

    fun setOnClickListener() {
        with(binding) {
            btnLoginSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }
}