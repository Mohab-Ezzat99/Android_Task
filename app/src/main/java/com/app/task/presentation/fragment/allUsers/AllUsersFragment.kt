package com.app.task.presentation.fragment.allUsers

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.app.task.base.BaseFragment
import com.app.task.databinding.FragmentAllUsersBinding
import com.app.task.util.LoadingDialog
import com.app.task.util.MyUtil.collect
import com.app.task.util.MyUtil.showMessage
import com.app.task.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllUsersFragment :
    BaseFragment<FragmentAllUsersBinding, AllUsersViewModel>(FragmentAllUsersBinding::inflate) {

    private val allUsersAdapter by lazy { AllUsersAdapter() }

    override fun viewModelClass(): Class<AllUsersViewModel> = AllUsersViewModel::class.java

    override fun FragmentAllUsersBinding.setUI(savedInstanceState: Bundle?) {
        rvCompetitions.adapter = allUsersAdapter
    }

    override fun observer() {
        collect(viewModel.allUsersResponse) {
            when (it) {
                Resource.Loading -> showLoading()

                is Resource.Success -> {
                    hideLoading()
                    it.value?.let { result ->
                        allUsersAdapter.setData(result)
                    }
                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessage(it.message.toString())
                }

                else -> {}
            }
        }
    }

    override fun FragmentAllUsersBinding.clicks() {
        fabAdd.setOnClickListener {
            findNavController().navigate(AllUsersFragmentDirections.actionAllUsersFragmentToAddUserFragment())
        }
    }

    override fun callApis() {
        viewModel.getAllUsers()
    }
}
