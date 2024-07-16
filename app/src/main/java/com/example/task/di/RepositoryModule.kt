package com.example.task.di

import com.example.task.data.repo.CategoryMealsMealsRepoImpl
import com.example.task.data.repo.HomeRepoImpl
import com.example.task.data.repo.MealDetailsRepoImpl
import com.example.task.domain.repo.CategoryMealsRepo
import com.example.task.domain.repo.HomeRepo
import com.example.task.domain.repo.MealDetailsRepo
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

    @Binds
    abstract fun getMealDetailsRepo(repo: MealDetailsRepoImpl): MealDetailsRepo

}