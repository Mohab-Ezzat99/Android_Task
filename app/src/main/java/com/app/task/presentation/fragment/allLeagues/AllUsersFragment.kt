package com.app.task.presentation.fragment.allLeagues

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.app.task.R
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
            viewModel.allLeaguesState.collect {
                if (it.isNullOrEmpty().not()) {
                    allUsersAdapter.setData(it)
                }
            }
        }
    }

    override fun FragmentAllUsersBinding.clicks() {
        fabAdd.setOnClickListener {

        }
    }

    override fun callApis() {
        viewModel.getAllUsers()
    }
}
