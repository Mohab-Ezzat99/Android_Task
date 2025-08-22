package com.app.task.domain.usecase

import com.app.task.R
import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.repository.MainRepository
import com.app.task.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddUserUseCase @Inject constructor(private val mainRepository: MainRepository) {
    operator fun invoke(userEntity: UserEntity): Flow<Resource<Long>> = flow {
        emit(Resource.Loading)

        if (userEntity.name.isEmpty()) {
            emit(Resource.Failure(messageId = R.string.empty_name))
            return@flow
        }

        if (userEntity.jobTitle.isEmpty()) {
            emit(Resource.Failure(messageId = R.string.empty_job_title))
            return@flow
        }

        if (userEntity.age.isEmpty()) {
            emit(Resource.Failure(messageId = R.string.empty_age))
            return@flow
        }

        emit(mainRepository.insertUser(userEntity))
    }.flowOn(Dispatchers.IO)
}
