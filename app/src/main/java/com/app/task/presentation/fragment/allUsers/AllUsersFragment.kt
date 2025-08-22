package com.app.task.presentation.fragment.allUsers

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.task.base.BaseFragment
import com.app.task.databinding.FragmentAllUsersBinding
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
        lifecycleScope.launchWhenStarted {
            viewModel.allUsersResponse.collect {
                if (it.isNullOrEmpty().not()) {
                    allUsersAdapter.setData(it)
                }
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
