package com.mundo.newsapiclient.domain.usecase

import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.domain.repository.ClientRepository

class DeleteSavedArticleUseCase(private val clientRepository: ClientRepository) {
    suspend fun execute(article: Article) = clientRepository.deleteSavedBusinessArticle(article)
}