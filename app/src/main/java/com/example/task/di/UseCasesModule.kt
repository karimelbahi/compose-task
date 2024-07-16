package com.example.task.di

import com.example.task.domain.usecases.*
import com.example.task.presentation.ui.category.CategoryMealsUseCase
import com.example.task.presentation.ui.home.HomeUseCase
import com.example.task.presentation.ui.mealdetails.MealDetailsUseCase
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
    abstract fun provideHomeUseCase(weatherUseCaseImpl: HomeUseCaseImpl): HomeUseCase

    @Singleton
    @Binds
    abstract fun provideCategoryMealsUseCase(weatherUseCaseImpl: CategoryMealsMealsUseCaseImpl): CategoryMealsUseCase

    @Singleton
    @Binds
    abstract fun provideMealDetailsUseCase(mealDetailsUseCaseImpl: MealDetailsUseCaseImpl): MealDetailsUseCase

}