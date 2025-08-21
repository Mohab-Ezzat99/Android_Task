package com.app.task.presentation.fragment.allLeagues

import androidx.lifecycle.viewModelScope
import com.app.task.base.BaseViewModel
import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : BaseViewModel() {

    private val _allLeaguesState by lazy { MutableStateFlow<List<UserEntity>?>(null) }
    val allLeaguesState: StateFlow<List<UserEntity>?> by lazy { _allLeaguesState }

    fun getAllUsers() {
        viewModelScope.launch {
            getAllUsersUseCase().collect {
                _allLeaguesState.value = it
            }
        }
    }
}
