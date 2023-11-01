package com.mundo.newsapiclient.data.repository.dataSourceImpl

import com.mundo.newsapiclient.data.api.BusinessAPIService
import com.mundo.newsapiclient.data.model.APIResponse
import com.mundo.newsapiclient.data.repository.dataSource.BusinessRemoteDataSource
import retrofit2.Response

class BusinessRemoteDataSourceImpl(
    private val businessAPIService: BusinessAPIService
) : BusinessRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return businessAPIService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedTopHeadlines(
        country: String,
        page: Int,
        query: String
    ): Response<APIResponse> {
        return businessAPIService.getSearchedTopHeadlines(country,query,page)
    }
}