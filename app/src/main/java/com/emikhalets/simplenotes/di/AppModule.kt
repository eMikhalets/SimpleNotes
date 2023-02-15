package com.emikhalets.simplenotes.di

import android.content.Context
import com.emikhalets.simplenotes.utils.AppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesPrefs(@ApplicationContext context: Context): AppDataStore = AppDataStore(context)
}