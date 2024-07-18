package com.example.network.di

import com.example.network.ResourcesResolver
import com.example.network.ResourcesResolverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ResourceModule {

    @Singleton
    @Binds
    abstract fun provideResourcesResolver(
        resourcesResolverImpl: ResourcesResolverImpl
    ): ResourcesResolver

}