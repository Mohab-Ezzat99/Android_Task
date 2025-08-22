package com.app.task.util

import android.widget.Toast
import androidx.fragment.app.Fragment

object MyUtil {

    fun Fragment.showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}