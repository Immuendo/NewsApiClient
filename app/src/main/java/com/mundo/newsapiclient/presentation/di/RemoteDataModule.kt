package com.mundo.newsapiclient.presentation.di

import com.mundo.newsapiclient.data.api.BusinessAPIService
import com.mundo.newsapiclient.data.repository.dataSource.BusinessRemoteDataSource
import com.mundo.newsapiclient.data.repository.dataSourceImpl.BusinessRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideBusinessRemoteDataSource(
        businessAPIService: BusinessAPIService
    ): BusinessRemoteDataSource{
        return BusinessRemoteDataSourceImpl(businessAPIService)
    }
}