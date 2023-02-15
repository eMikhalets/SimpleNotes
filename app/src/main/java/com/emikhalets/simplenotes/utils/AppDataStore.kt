package com.emikhalets.simplenotes.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = NAME)

    suspend fun collectCheckedTasksVisible(block: (Boolean) -> Unit) = context.dataStore.data
        .map { it[CHECKED_TASKS_VISIBLE] ?: false }
        .collectLatest { block(it) }

    suspend fun changeCheckedTasksVisible() = context.dataStore.edit {
        val current = it[CHECKED_TASKS_VISIBLE] ?: false
        it[CHECKED_TASKS_VISIBLE] = !current
    }

    companion object {
        private const val NAME = "Settings"

        private val CHECKED_TASKS_VISIBLE = booleanPreferencesKey("CHECKED_TASKS_VISIBLE")
    }
}