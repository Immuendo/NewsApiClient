package com.mundo.newsapiclient.data.repository.dataSource

import com.mundo.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

interface BusinessLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
    fun getSavedBusinessArticles():Flow<List<Article>>
    suspend fun deleteArticleFromDB(article: Article)
}