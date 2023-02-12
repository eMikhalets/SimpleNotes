package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(entity: TaskEntity) = repository.deleteTask(entity)
}