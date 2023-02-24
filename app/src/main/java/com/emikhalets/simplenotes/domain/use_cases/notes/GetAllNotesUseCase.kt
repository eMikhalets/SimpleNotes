package com.emikhalets.simplenotes.domain.use_cases.notes

import com.emikhalets.simplenotes.domain.AppRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val repository: AppRepository) {

    operator fun invoke() = repository.getNotes()
}