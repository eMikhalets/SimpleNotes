package com.emikhalets.simplenotes.domain.use_cases.tasks

import com.emikhalets.simplenotes.domain.AppRepository
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val repository: AppRepository) {

    operator fun invoke(id: Long) = repository.getTask(id)
}