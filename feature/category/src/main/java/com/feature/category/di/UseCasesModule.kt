package com.feature.category.di


import com.feature.category.domain.usecases.CategoryMealsMealsUseCaseImpl
import com.feature.category.presentation.CategoryMealsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCasesModule {


    @Singleton
    @Binds
    abstract fun provideCategoryMealsUseCase(weatherUseCaseImpl: CategoryMealsMealsUseCaseImpl): CategoryMealsUseCase


}