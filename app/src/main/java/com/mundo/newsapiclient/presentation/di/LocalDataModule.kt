package com.mundo.newsapiclient.presentation.di

import com.mundo.newsapiclient.data.db.ArticleDao
import com.mundo.newsapiclient.data.repository.dataSource.BusinessLocalDataSource
import com.mundo.newsapiclient.data.repository.dataSourceImpl.BusinessLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideBusinessLocalDataSource(
        articleDao: ArticleDao
    ): BusinessLocalDataSource{
        return BusinessLocalDataSourceImpl(articleDao)
    }
}