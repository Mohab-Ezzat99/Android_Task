package com.app.task.presentation.fragment.addUser

import androidx.lifecycle.viewModelScope
import com.app.task.base.BaseViewModel
import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.usecase.AddUserUseCase
import com.app.task.domain.usecase.GetAllUsersUseCase
import com.app.task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : BaseViewModel() {

    private val _addUserResponse by lazy { MutableSharedFlow<Resource<Long>>() }
    val addUserResponse by lazy { _addUserResponse }

    fun addUser(userEntity: UserEntity) {
        addUserUseCase(userEntity)
            .onEach { _addUserResponse.emit(it) }
            .launchIn(viewModelScope)
    }
}
