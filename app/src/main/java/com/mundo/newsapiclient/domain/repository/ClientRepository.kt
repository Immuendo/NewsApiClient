package com.mundo.newsapiclient.domain.repository

import com.mundo.newsapiclient.data.model.APIResponse
import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ClientRepository {

    suspend fun getBusinessArticles(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedBusinessArticles(country: String, page: Int, searchQuery: String): Resource<APIResponse>
    suspend fun saveBusinessArticle(article: Article)
    suspend fun deleteSavedBusinessArticle(article: Article)
    fun getSavedBusinessArticles(): Flow<List<Article>>

}