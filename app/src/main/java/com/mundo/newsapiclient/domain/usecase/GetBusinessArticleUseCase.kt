package com.mundo.newsapiclient.domain.usecase

import com.mundo.newsapiclient.data.model.APIResponse
import com.mundo.newsapiclient.data.util.Resource
import com.mundo.newsapiclient.domain.repository.ClientRepository

class GetBusinessArticleUseCase(private val clientRepository: ClientRepository) {
    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return clientRepository.getBusinessArticles(country, page)
    }
}