package com.emikhalets.simplenotes.domain.entities

data class TaskEntity(
    val id: Long = 0,
    val content: String,
    val checked: Boolean = false,
    val sortOrder: Int = 0,
    val savedTime: Long = 0,
)
