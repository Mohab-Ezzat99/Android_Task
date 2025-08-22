package com.app.task.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.app.task.databinding.ProgressBinding

class LoadingDialog(
    private val activity: Activity?
) {

    private var _binding: ProgressBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var _dialog: Dialog? = null
    private val dialog get() = checkNotNull(_dialog)

    init {
        initViewBinding()
        initDialog()
    }

    private fun initViewBinding() {
        _binding = ProgressBinding.inflate(LayoutInflater.from(activity))
    }

    private fun initDialog() {
        _dialog = Dialog(checkNotNull(activity)).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setContentView(binding.root)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    fun showDialog() {
        if (activity != null && !activity.isFinishing) {
            hideDialog()
            dialog.show()
        }
    }

    fun hideDialog() {
        if (activity != null && !activity.isFinishing && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun clean() {
        dialog.dismiss()
        _binding = null
        _dialog = null
    }
}