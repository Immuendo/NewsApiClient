package com.mundo.newsapiclient.presentation.di

import com.mundo.newsapiclient.presentation.adapter.ArticleAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideBusinessAdapter(): ArticleAdapter{
        return ArticleAdapter()
    }
}