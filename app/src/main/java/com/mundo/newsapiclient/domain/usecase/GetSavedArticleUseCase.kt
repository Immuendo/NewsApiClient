package com.mundo.newsapiclient.domain.usecase

import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticleUseCase(private val clientRepository: ClientRepository) {
    fun execute(): Flow<List<Article>> {
        return clientRepository.getSavedBusinessArticles()
    }
}