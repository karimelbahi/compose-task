package com.example.task.di

import com.example.task.data.repo.CategoryMealsMealsRepoImpl
import com.example.task.data.repo.HomeRepoImpl
import com.example.task.domain.repo.CategoryMealsRepo
import com.example.task.domain.repo.HomeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getPostsRepo(repo: HomeRepoImpl): HomeRepo

    @Binds
    abstract fun getCategoryMealsRepo(repo: CategoryMealsMealsRepoImpl): CategoryMealsRepo

}