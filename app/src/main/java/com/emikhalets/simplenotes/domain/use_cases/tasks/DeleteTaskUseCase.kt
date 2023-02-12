package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository
import com.emikhalets.simplenotes.domain.entities.TaskEntity

class DeleteTaskUseCase(private val repository: AppRepository) {

    operator fun invoke(entity: TaskEntity) = repository.deleteTask(entity)
}