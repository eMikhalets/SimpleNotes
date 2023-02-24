package com.emikhalets.simplenotes.domain.entities

data class NoteEntity(
    val id: Long = 0,
    val title: String,
    val content: String,
    val savedTime: Long = 0,
)
