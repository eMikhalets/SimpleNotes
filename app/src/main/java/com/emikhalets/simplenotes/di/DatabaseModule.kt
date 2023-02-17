package com.emikhalets.simplenotes.di

import android.content.Context
import com.emikhalets.simplenotes.data.database.AppDatabase
import com.emikhalets.simplenotes.data.database.NotesDao
import com.emikhalets.simplenotes.data.database.TasksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun providesTasksDao(database: AppDatabase): TasksDao = database.tasksDao

    @Provides
    fun providesNotesDao(database: AppDatabase): NotesDao = database.notesDao
}