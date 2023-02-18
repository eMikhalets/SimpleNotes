package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(private val repository: AppRepository) {

    operator fun invoke() = repository.getTasks()
}