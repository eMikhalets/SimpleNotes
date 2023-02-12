package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository

class GetTaskUseCase(private val repository: AppRepository) {

    operator fun invoke(id: Long) = repository.getTask(id)
}