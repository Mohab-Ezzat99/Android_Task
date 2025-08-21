package com.app.task.domain.usecase

import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllUsersUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(): Flow<List<UserEntity>?> = flow {
        emit(mainRepository.getAllUsers())
    }
}
