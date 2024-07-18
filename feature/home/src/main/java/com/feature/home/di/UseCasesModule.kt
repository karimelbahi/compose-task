package com.feature.home.di

import com.feature.home.domain.usecases.HomeUseCaseImpl
import com.feature.home.presentation.HomeUseCase
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

}