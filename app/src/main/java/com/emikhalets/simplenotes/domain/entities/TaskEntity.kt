package com.emikhalets.simplenotes.domain.entities

data class TaskEntity(
    val id: Long,
    val content: String,
    val checked: Boolean,
)
