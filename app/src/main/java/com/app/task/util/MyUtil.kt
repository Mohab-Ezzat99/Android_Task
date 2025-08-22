package com.app.task.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

object MyUtil {

    fun Fragment.showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun <T> Fragment.collect(sharedFlow: Flow<T>, block: (T) -> Unit) {
        lifecycleScope.launch {
            sharedFlow.collect { block(it) }
        }
    }

}