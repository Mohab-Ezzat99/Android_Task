package com.app.task.presentation

import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.app.task.R
import com.app.task.base.BaseActivity
import com.app.task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var navController: NavController
    
    override fun ActivityMainBinding.setUI(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.hostMain) as NavHostFragment
        navController = navHostFragment.findNavController()
        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun observer() {
    }

    override fun ActivityMainBinding.clicks() {

    }

    override fun callApis() {
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
