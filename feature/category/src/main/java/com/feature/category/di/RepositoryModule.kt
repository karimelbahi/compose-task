package com.feature.category.di


import com.feature.category.data.repo.CategoryMealsMealsRepoImpl
import com.feature.category.domain.repo.CategoryMealsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getCategoryMealsRepo(repo: CategoryMealsMealsRepoImpl): CategoryMealsRepo


}