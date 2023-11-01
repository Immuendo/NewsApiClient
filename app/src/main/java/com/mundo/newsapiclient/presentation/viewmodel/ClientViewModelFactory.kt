package com.mundo.newsapiclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mundo.newsapiclient.domain.usecase.DeleteSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetBusinessArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSavedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.GetSearchedArticleUseCase
import com.mundo.newsapiclient.domain.usecase.SaveBusinessArticleUseCase

class ClientViewModelFactory(
    private val app: Application,
    private val getBusinessArticleUseCase: GetBusinessArticleUseCase,
    private val getSearchedArticleUseCase: GetSearchedArticleUseCase,
    private val saveBusinessArticleUseCase: SaveBusinessArticleUseCase,
    private val getSavedArticleUseCase: GetSavedArticleUseCase,
    private val deleteSavedArticleUseCase: DeleteSavedArticleUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientViewModel(
            app,
            getBusinessArticleUseCase,
            getSearchedArticleUseCase,
            saveBusinessArticleUseCase,
            getSavedArticleUseCase,
            deleteSavedArticleUseCase
        ) as T
    }
}