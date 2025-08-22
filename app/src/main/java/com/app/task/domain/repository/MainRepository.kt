package com.app.task.domain.repository

import com.app.task.data.local.entity.UserEntity
import com.app.task.util.Resource

interface MainRepository {

    suspend fun insertUser(userEntity: UserEntity) : Resource<Long>

    suspend fun getAllUsers(): Resource<List<UserEntity>?>
}
