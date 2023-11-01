package com.mundo.newsapiclient.data.repository.dataSource

import com.mundo.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface BusinessRemoteDataSource {

    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
    suspend fun getSearchedTopHeadlines(country: String, page: Int, query: String): Response<APIResponse>
}