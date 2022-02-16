package com.dreamhomefurniturecompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*

@InstallIn(SingletonComponent::class)
@Module
class CoroutineDependencies {

    @DelicateCoroutinesApi
    @Provides
    fun providesExternAlScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}