package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository
import com.emikhalets.simplenotes.domain.entities.TaskEntity

class InsertTaskUseCase(private val repository: AppRepository) {

    operator fun invoke(entity: TaskEntity) = repository.insertTask(entity)
}