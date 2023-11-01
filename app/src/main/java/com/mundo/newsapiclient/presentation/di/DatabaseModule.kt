package com.mundo.newsapiclient.presentation.di

import android.app.Application
import androidx.room.Room
import com.mundo.newsapiclient.data.db.ArticleDao
import com.mundo.newsapiclient.data.db.BusinessDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideBusinessDatabase(app:Application):BusinessDatabase{
        return Room.databaseBuilder(app, BusinessDatabase::class.java,"business_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(businessDB: BusinessDatabase):ArticleDao{
        return businessDB.getArticleDao()
    }
}