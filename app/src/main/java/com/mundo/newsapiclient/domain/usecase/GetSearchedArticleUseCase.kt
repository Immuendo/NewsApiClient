package com.mundo.newsapiclient.domain.usecase

import com.mundo.newsapiclient.data.model.APIResponse
import com.mundo.newsapiclient.data.util.Resource
import com.mundo.newsapiclient.domain.repository.ClientRepository

class GetSearchedArticleUseCase(private val clientRepository: ClientRepository) {
    suspend fun execute(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> {
        return clientRepository.getSearchedBusinessArticles(country,page, searchQuery)
    }
}