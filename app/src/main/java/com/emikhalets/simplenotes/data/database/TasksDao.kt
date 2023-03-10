package com.emikhalets.simplenotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emikhalets.simplenotes.data.database.entities.TaskDb
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    suspend fun insert(entity: TaskDb)

    @Update
    suspend fun update(entity: TaskDb)

    @Update
    suspend fun update(entities: List<TaskDb>)

    @Delete
    suspend fun delete(entity: TaskDb)

    @Query("SELECT * FROM tasks_table ORDER BY saved_time DESC")
    fun getAllFlow(): Flow<List<TaskDb>>

    @Query("SELECT * FROM tasks_table WHERE id = :id")
    fun getItemFlow(id: Long): Flow<TaskDb>
}