package com.emikhalets.simplenotes.presentation.screens.pager

import androidx.lifecycle.ViewModel
import com.emikhalets.simplenotes.utils.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor(
    val dataStore: AppDataStore,
) : ViewModel()
