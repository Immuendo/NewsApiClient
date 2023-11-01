package com.mundo.newsapiclient.data.repository

import com.mundo.newsapiclient.data.model.APIResponse
import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.data.repository.dataSource.BusinessLocalDataSource
import com.mundo.newsapiclient.data.repository.dataSource.BusinessRemoteDataSource
import com.mundo.newsapiclient.data.util.Resource
import com.mundo.newsapiclient.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ClientRepositoryImpl(
    private val businessRemoteDataSource: BusinessRemoteDataSource,
    private val businessLocalDataSource: BusinessLocalDataSource
) : ClientRepository {
    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getBusinessArticles(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(businessRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedBusinessArticles(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<APIResponse> {
        return responseToResource(businessRemoteDataSource.getSearchedTopHeadlines(country,page,searchQuery))
    }

    override suspend fun saveBusinessArticle(article: Article) {
        businessLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteSavedBusinessArticle(article: Article) {
        businessLocalDataSource.deleteArticleFromDB(article)
    }

    override fun getSavedBusinessArticles(): Flow<List<Article>> {
       return businessLocalDataSource.getSavedBusinessArticles()
    }
}