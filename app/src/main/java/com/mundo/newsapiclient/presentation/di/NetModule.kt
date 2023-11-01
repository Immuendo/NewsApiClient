package com.mundo.newsapiclient.presentation.di

import com.mundo.newsapiclient.BuildConfig
import com.mundo.newsapiclient.data.api.BusinessAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBusinessAPIService(retrofit: Retrofit): BusinessAPIService{
        return retrofit.create(BusinessAPIService::class.java)
    }
}