package com.app.task.domain.usecase

import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.repository.MainRepository
import com.app.task.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllUsersUseCase @Inject constructor(private val mainRepository: MainRepository) {
    operator fun invoke(): Flow<Resource<List<UserEntity>?>> = flow {
        emit(Resource.Loading)
        emit(mainRepository.getAllUsers())
    }.flowOn(Dispatchers.IO)
}
