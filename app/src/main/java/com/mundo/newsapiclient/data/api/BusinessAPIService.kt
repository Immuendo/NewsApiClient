package com.mundo.newsapiclient.data.api

import com.mundo.newsapiclient.BuildConfig
import com.mundo.newsapiclient.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessAPIService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("category")
        category: String = BuildConfig.CATEGORY_QUERY,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>

    @GET("/v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country: String,
        @Query("q")
        query: String,
        @Query("page")
        page: Int,
        @Query("category")
        category: String = BuildConfig.CATEGORY_QUERY,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>

}