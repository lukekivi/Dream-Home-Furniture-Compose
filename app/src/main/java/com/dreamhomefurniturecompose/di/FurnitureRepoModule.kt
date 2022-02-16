package com.dreamhomefurniturecompose.di

import com.dreamhomefurniturecompose.data.FurnitureRepo
import com.dreamhomefurniturecompose.data.FurnitureRepoImpl
import com.dreamhomefurniturecompose.network.FurnitureNetworkService
import com.dreamhomefurniturecompose.network.FurnitureNetworkServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface FurnitureRepoModule {

    @Binds
    fun bindsFurnitureNetworkService(furnitureNetworkService: FurnitureNetworkServiceImpl): FurnitureNetworkService

    @Binds
    fun bindsFurnitureRepository(furnitureRepo: FurnitureRepoImpl): FurnitureRepo
}