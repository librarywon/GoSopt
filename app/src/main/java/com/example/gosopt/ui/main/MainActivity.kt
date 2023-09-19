package com.example.gosopt.ui.feature.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gosopt.R
import com.example.gosopt.databinding.ActivityMainBinding
import com.example.gosopt.ui.main.gallery.GalleryFragment
import com.example.gosopt.ui.main.git.GitFragment
import com.example.gosopt.ui.main.home.HomeFragment
import com.example.gosopt.ui.main.search.SearchFragment
import com.example.gosopt.util.toast

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        toast(getUserInfo().first.toString() + " " + getUserInfo().second.toString())

        setOnClickListener()
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bnvMain.selectedItemId = R.id.menu_home
        }
    }

    private fun setOnClickListener() {
        binding.bnvMain.setOnItemSelectedListener() { item ->
            toast(item.itemId.toString())
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_git -> {
                    replaceFragment(GitFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_gallery -> {
                    replaceFragment(GalleryFragment())
                    true
                }

                else -> false
            }
        }
    }


    private fun getUserInfo(): Pair<String?, String?> {
        val sharedPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", null)
        val hobby = sharedPreferences.getString("hobby", null)

        return Pair(name, hobby)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    override fun onBackPressed() {
        finishAffinity()  // 모든 액티비티 스택을 종료
    }
}