package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository

class GetAllTasksUseCase(private val repository: AppRepository) {

    operator fun invoke() = repository.getTasks()
}