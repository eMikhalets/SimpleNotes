package com.emikhalets.simplenotes.di

import com.emikhalets.simplenotes.data.repository.AppRepositoryImpl
import com.emikhalets.simplenotes.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsAppRepository(impl: AppRepositoryImpl): AppRepository
}