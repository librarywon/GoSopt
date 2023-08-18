package com.example.gosopt.feature.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosopt.data.User
import com.example.gosopt.databinding.ActivityLoginBinding
import com.example.gosopt.feature.home.HomeActivity
import com.example.gosopt.feature.signup.SignupActivity
import com.example.gosopt.util.hideKeyboard
import com.example.gosopt.util.toast

class LoginActivity : AppCompatActivity() {
    //by lazy를 사용하면 해당 변수가 사용될 때 초기화가 진행됩니다.
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        receiveData()
        autoLogin()
        setOnClickListener()
    }

    private fun receiveData() {
        val receivedId = intent.getStringExtra("id").toString()
        val receivedPw = intent.getStringExtra("pw").toString()
        val receivedName = intent.getStringExtra("name").toString()
        val receivedHobby = intent.getStringExtra("hobby").toString()

        if(!intent.getStringExtra("id").isNullOrBlank()){
            binding.etLoginId.setText(receivedId)
        }

        viewModel.setUserInfo(receivedId, receivedPw, receivedName, receivedHobby)
    }
    private fun autoLogin() {
        // 자동 로그인 처리
        val (id, pw) = getLoginInfo()
        if (id != null && pw != null) {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
    private fun setOnClickListener() {
        with(binding) {
            root.setOnClickListener {
                hideKeyboard(it)
            }

            btnLoginSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intent)
            }

            btnLogin.setOnClickListener {
                val id =viewModel.userInfo.value?.id
                val pw = viewModel.userInfo.value?.pw

                if (etLoginId.text.toString() == id &&  etLoginPw.text.toString() ==pw ) {
                    toast("로그인 성공")
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    saveLoginInfo()
                    startActivity(intent)
                } else {
                    toast("아이디와 비밀번호를 확인해주세요")
                }
            }
        }
    }

    // SharedPreferences에서 로그인 정보를 가져오는 함수
    private fun getLoginInfo(): Pair<String?, String?> {
        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("id", null)
        val pw = sharedPreferences.getString("pw", null)

        return Pair(id, pw)
    }

    // SharedPreferences에 로그인 정보를 저장하는 함수
    private fun saveLoginInfo() {
        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val userInfo = viewModel.userInfo.value ?: User("","","","")

        editor.putString("id", userInfo.id)
        editor.putString("pw", userInfo.pw)
        editor.putString("name", userInfo.name)
        editor.putString("hobby", userInfo.hobby)
        editor.apply()
    }
}