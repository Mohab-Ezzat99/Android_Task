package com.app.task.di

import android.app.Application
import androidx.room.Room
import com.app.task.data.local.AppDB
import com.app.task.data.reporsitory.MainRepositoryImpl
import com.app.task.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDB(app: Application): AppDB {
        return Room.databaseBuilder(app, AppDB::class.java, "AppDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDao(appDB: AppDB) = appDB.dao

    @Provides
    @Singleton
    fun provideMainRepository(db: AppDB): MainRepository {
        return MainRepositoryImpl(db.dao)
    }
}
