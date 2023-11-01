package com.mundo.newsapiclient.presentation.di

import com.mundo.newsapiclient.domain.repository.ClientRepository
import com.mundo.newsapiclient.domain.usecase.DeleteSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetBusinessArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSearchedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.SaveBusinessArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetBusinessHeadlineUseCase(
        clientRepository: ClientRepository
    ):GetBusinessArticleUseCase{
        return GetBusinessArticleUseCase(clientRepository)
    }

    @Provides
    @Singleton
    fun provideGetSearchedBusinessUseCase(
        clientRepository: ClientRepository
    ):GetSearchedArticleUseCase{
        return GetSearchedArticleUseCase(clientRepository)
    }

    @Provides
    @Singleton
    fun provideSaveBusinessHeadlineUseCase(
        clientRepository: ClientRepository
    ):SaveBusinessArticleUseCase{
        return SaveBusinessArticleUseCase(clientRepository)
    }

    @Provides
    @Singleton
    fun provideGetSavedArticleUseCase(
        clientRepository: ClientRepository
    ):GetSavedArticleUseCase{
        return GetSavedArticleUseCase(clientRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSavedArticleUseCase(
        clientRepository: ClientRepository
    ):DeleteSavedArticleUseCase{
        return DeleteSavedArticleUseCase(clientRepository)
    }
}