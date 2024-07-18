package com.feature.home.di

import com.feature.home.data.api.retrofit.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun getWebservice(retrofit: Retrofit) =
        retrofit.create(WebService::class.java)

}