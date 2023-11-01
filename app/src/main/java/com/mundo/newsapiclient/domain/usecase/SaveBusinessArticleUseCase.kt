package com.mundo.newsapiclient.domain.usecase

import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.domain.repository.ClientRepository

class SaveBusinessArticleUseCase(private val clientRepository: ClientRepository) {
    suspend fun execute(article: Article) = clientRepository.saveBusinessArticle(article)
}