package com.example.gosopt.feature.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gosopt.R
import com.example.gosopt.databinding.ActivityHomeBinding
import com.example.gosopt.util.toast

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toast(getUserInfo().first.toString() + " " + getUserInfo().second.toString())
    }

    private fun getUserInfo(): Pair<String?, String?> {
        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", null)
        val hobby = sharedPreferences.getString("hobby", null)

        return Pair(name, hobby)
    }
}