package com.feature.home.di

import com.feature.home.data.repo.HomeRepoImpl
import com.feature.home.domain.repo.HomeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getPostsRepo(repo: HomeRepoImpl): HomeRepo

}