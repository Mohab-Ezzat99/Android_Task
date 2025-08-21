package com.app.task.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.app.task.R

abstract class BaseActivity<B : ViewBinding>(
    open val bindingInflater: (LayoutInflater) -> B
) : AppCompatActivity() {

    private var _binding: B? = null
    open val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)

        binding.setUI(savedInstanceState)
        binding.clicks()
        callApis()
        observer()
    }

    open fun B.setUI(savedInstanceState: Bundle?) {}
    open fun B.clicks() {}
    open fun callApis() {}
    open fun observer() {}

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_change, R.anim.slide_down)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
