package com.emikhalets.simplenotes.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TaskDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "checked") val checked: Boolean,
    @ColumnInfo(name = "saved_time", defaultValue = "0") val savedTime: Long,
)
