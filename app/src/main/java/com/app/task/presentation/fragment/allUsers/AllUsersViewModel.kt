package com.app.task.presentation.fragment.allUsers

import androidx.lifecycle.viewModelScope
import com.app.task.base.BaseViewModel
import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.usecase.GetAllUsersUseCase
import com.app.task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : BaseViewModel() {

    private val _allUsersResponse by lazy { MutableStateFlow<Resource<List<UserEntity>?>>(Resource.Default) }
    val allUsersResponse by lazy { _allUsersResponse }

    fun getAllUsers() {
        getAllUsersUseCase()
            .onEach { _allUsersResponse.emit(it) }
            .launchIn(viewModelScope)
    }
}
