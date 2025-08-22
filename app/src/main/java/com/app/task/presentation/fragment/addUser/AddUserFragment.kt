package com.app.task.presentation.fragment.addUser

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.task.R
import com.app.task.base.BaseFragment
import com.app.task.data.local.entity.UserEntity
import com.app.task.databinding.FragmentAddUserBinding
import com.app.task.util.LoadingDialog
import com.app.task.util.MyUtil.collect
import com.app.task.util.MyUtil.showMessage
import com.app.task.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment :
    BaseFragment<FragmentAddUserBinding, AddUserViewModel>(FragmentAddUserBinding::inflate) {

    override fun viewModelClass(): Class<AddUserViewModel> = AddUserViewModel::class.java

    override fun observer() {
        collect(viewModel.addUserResponse) {
            when (it) {
                Resource.Loading -> showLoading()

                is Resource.Success -> {
                    hideLoading()
                    binding.tvError.text = ""
                    showMessage(getString(R.string.user_added_successfully))
                    findNavController().navigate(AddUserFragmentDirections.actionAddUserFragmentToAllUsersFragment())
                }

                is Resource.Failure -> {
                    hideLoading()
                    it.messageId?.let { message ->
                        binding.tvError.text = getString(message)
                    } ?: showMessage(it.message ?: "")
                }

                else -> {}
            }
        }
    }

    override fun FragmentAddUserBinding.clicks() {
        btnConfirm.setOnClickListener {
            val selectedGender = if (rgGender.checkedRadioButtonId == rbMale.id) {
                getString(R.string.male)
            } else {
                getString(R.string.female)
            }
            viewModel.addUser(
                UserEntity(
                    name = etName.text.toString().trim(),
                    jobTitle = etJobTitle.text.toString().trim(),
                    age = etAge.text.toString().trim(),
                    gender = selectedGender
                )
            )
        }
    }
}
