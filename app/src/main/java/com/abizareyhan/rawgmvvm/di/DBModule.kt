package com.abizareyhan.rawgmvvm.di

import android.app.Application
import androidx.room.Room
import com.abizareyhan.data.db.GameDatabase
import com.abizareyhan.data.db.GameDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideGameDatabase(app: Application): GameDatabase {
        return Room.databaseBuilder(app.applicationContext, GameDatabase::class.java, "GameDatabase")
            .build()
    }

    @Provides
    @Singleton
    fun provideGameDetailDao(
        database: GameDatabase
    ): GameDetailDao {
        return database.gameDetailDao()
    }
}