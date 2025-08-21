package com.app.task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.task.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDB : RoomDatabase() {
    abstract val dao: AppDao
}
