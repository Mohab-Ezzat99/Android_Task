package com.app.task.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.task.data.local.entity.UserEntity

@Dao
interface AppDao {

    @Upsert
    suspend fun insertUser(userEntity: UserEntity) : Long

    @Query("SELECT * FROM UserEntity")
    suspend fun getAllUsers(): List<UserEntity>
}
