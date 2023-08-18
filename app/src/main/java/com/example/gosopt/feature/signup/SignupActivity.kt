package com.example.gosopt.feature.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosopt.databinding.ActivitySignupBinding
import com.example.gosopt.feature.login.LoginActivity
import com.example.gosopt.util.hideKeyboard
import com.example.gosopt.util.snackbar
import com.example.gosopt.util.toast

class SignupActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    private val viewModel = SignupViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListener()
    }

    fun setOnClickListener() {
        with(binding) {
            root.setOnClickListener {
                hideKeyboard(it)
            }

            btnSignupComplete.setOnClickListener {
                val id = etSignupId.text.toString()
                val pw = etSignupPw.text.toString()
                val name = etSignupName.text.toString()
                val hobby = etSignupHobby.text.toString()
                if (viewModel.checkId(id) and viewModel.checkPw(pw)) {
                    snackbar("회원가입 완료")

                    // 회원가입 정보를 로그인 액티비티로 전달
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("pw", pw)
                    intent.putExtra("name", name)
                    intent.putExtra("hobby", hobby)

                    startActivity(intent)
                } else {
                    toast("아이디와 비밀번호를 확인해주세요")
                }
            }
        }
    }


}