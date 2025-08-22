package com.app.task.data.reporsitory

import com.app.task.data.local.AppDao
import com.app.task.data.local.entity.UserEntity
import com.app.task.domain.repository.MainRepository
import com.app.task.util.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dao: AppDao,
) : MainRepository {

    override suspend fun insertUser(userEntity: UserEntity) : Resource<Long> {
        return Resource.Success(dao.insertUser(userEntity))
    }

    override suspend fun getAllUsers(): Resource<List<UserEntity>> {
        return Resource.Success(dao.getAllUsers())
    }

}
