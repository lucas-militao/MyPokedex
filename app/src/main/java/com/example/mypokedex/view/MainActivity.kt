package com.example.mypokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.mypokedex.R
import com.example.mypokedex.databinding.NavigationActivityBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: NavigationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.navigation_activity)
        setupView()
        subscribeUI()
    }

    private fun setupView() {

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment ?: return

        val navController = host.navController

        setSupportActionBar(binding.toolbar)

    }

    private fun subscribeUI() {

    }
}