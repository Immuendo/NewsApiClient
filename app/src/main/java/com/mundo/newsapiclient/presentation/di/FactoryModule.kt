package com.mundo.newsapiclient.presentation.di

import android.app.Application
import com.mundo.newsapiclient.domain.usecase.DeleteSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetBusinessArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSearchedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.SaveBusinessArticleUseCase
import com.mundo.newsapiclient.presentation.viewmodel.ClientViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideClientViewModelFactory(
        app: Application,
        getBusinessArticleUseCase: GetBusinessArticleUseCase,
        searchedArticleUseCase: GetSearchedArticleUseCase,
        saveBusinessArticleUseCase: SaveBusinessArticleUseCase,
        getSavedArticleUseCase: GetSavedArticleUseCase,
        deleteSavedArticleUseCase: DeleteSavedArticleUseCase
    ): ClientViewModelFactory{
        return ClientViewModelFactory(
            app,
            getBusinessArticleUseCase,
            searchedArticleUseCase,
            saveBusinessArticleUseCase,
            getSavedArticleUseCase,
            deleteSavedArticleUseCase
        )
    }
}