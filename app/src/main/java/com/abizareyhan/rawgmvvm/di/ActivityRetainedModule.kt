package com.abizareyhan.rawgmvvm.di

import com.abizareyhan.data.repository.GameLocalDatabaseRepositoryImpl
import com.abizareyhan.data.repository.RAWGApiRepositoryImpl
import com.abizareyhan.domain.repository.GameLocalDatabaseRepository
import com.abizareyhan.domain.repository.RAWGApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindRAWGApiRepository(repository: RAWGApiRepositoryImpl): RAWGApiRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindGameLocalDatabaseRepository(repository: GameLocalDatabaseRepositoryImpl): GameLocalDatabaseRepository
}