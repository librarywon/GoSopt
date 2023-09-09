package com.example.gosopt.ui.feature.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosopt.R
import com.example.gosopt.databinding.ActivityMainBinding
import com.example.gosopt.ui.main.git.GitFragment
import com.example.gosopt.util.toast

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast(getUserInfo().first.toString() + " " + getUserInfo().second.toString())

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, GitFragment())
            .commit()
    }

    private fun getUserInfo(): Pair<String?, String?> {
        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", null)
        val hobby = sharedPreferences.getString("hobby", null)

        return Pair(name, hobby)
    }

    override fun onBackPressed() {
        finishAffinity()  // 모든 액티비티 스택을 종료
    }
}