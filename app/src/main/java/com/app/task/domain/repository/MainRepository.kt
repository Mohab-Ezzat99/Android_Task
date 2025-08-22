package com.app.task.domain.repository

import com.app.task.data.local.entity.UserEntity

interface MainRepository {

    suspend fun insertUser(userEntity: UserEntity) : Long

    suspend fun getAllUsers(): List<UserEntity>?
}
