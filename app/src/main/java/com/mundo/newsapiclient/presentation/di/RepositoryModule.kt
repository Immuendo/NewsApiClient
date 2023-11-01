package com.mundo.newsapiclient.presentation.di

import com.mundo.newsapiclient.data.repository.ClientRepositoryImpl
import com.mundo.newsapiclient.data.repository.dataSource.BusinessLocalDataSource
import com.mundo.newsapiclient.data.repository.dataSource.BusinessRemoteDataSource
import com.mundo.newsapiclient.domain.repository.ClientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideClientRepository(
        businessRemoteDataSource: BusinessRemoteDataSource,
        businessLocalDataSource: BusinessLocalDataSource
    ): ClientRepository {
        return ClientRepositoryImpl(businessRemoteDataSource,businessLocalDataSource)
    }
}