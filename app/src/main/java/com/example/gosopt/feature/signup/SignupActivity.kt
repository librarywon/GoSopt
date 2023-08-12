package com.example.gosopt.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.gosopt.R
import com.example.gosopt.databinding.ActivitySignupBinding
import com.example.gosopt.feature.login.LoginActivity

class SignupActivity : AppCompatActivity() {
    val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    var viewModel = SignupViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListener()
    }

    fun setOnClickListener() {
        with(binding) {
            btnSignupComplete.setOnClickListener {
                val id = etSignupId.text.toString()
                val pw = etSignupPw.text.toString()
                if(viewModel.checkId(id) and viewModel.checkPw(pw)) {
                    toast("회원가입 완료")
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                else {
                    toast("아이디와 비밀번호를 확인해주세요")
                }
            }
        }
    }

    fun toast(text:String) {
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
    }

}