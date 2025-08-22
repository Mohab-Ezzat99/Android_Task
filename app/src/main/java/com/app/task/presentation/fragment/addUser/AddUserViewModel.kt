package com.app.task.presentation.fragment.addUser

import androidx.lifecycle.viewModelScope
import com.app.task.base.BaseViewModel
import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.usecase.AddUserUseCase
import com.app.task.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : BaseViewModel() {

    private val _addUserResponse by lazy { MutableSharedFlow<Long>() }
    val addUserResponse: SharedFlow<Long> by lazy { _addUserResponse }

    fun addUser(userEntity: UserEntity) {
        viewModelScope.launch {
            addUserUseCase(userEntity).collect {
                _addUserResponse.emit(it)
            }
        }
    }
}
