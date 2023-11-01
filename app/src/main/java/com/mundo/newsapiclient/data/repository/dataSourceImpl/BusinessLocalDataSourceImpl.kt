package com.mundo.newsapiclient.data.repository.dataSourceImpl

import com.mundo.newsapiclient.data.db.ArticleDao
import com.mundo.newsapiclient.data.model.Article
import com.mundo.newsapiclient.data.repository.dataSource.BusinessLocalDataSource
import kotlinx.coroutines.flow.Flow

class BusinessLocalDataSourceImpl(
    private var articleDao: ArticleDao
): BusinessLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDao.insert(article)
    }

    override fun getSavedBusinessArticles(): Flow<List<Article>> {
        return articleDao.getBusinessArticles()
    }

    override suspend fun deleteArticleFromDB(article: Article) {
        articleDao.deleteArticle(article)
    }
}