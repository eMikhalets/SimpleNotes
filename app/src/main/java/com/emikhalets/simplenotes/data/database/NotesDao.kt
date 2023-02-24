package com.emikhalets.simplenotes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emikhalets.simplenotes.data.database.entities.NoteDb
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    suspend fun insert(entity: NoteDb)

    @Update
    suspend fun update(entity: NoteDb)

    @Delete
    suspend fun delete(entity: NoteDb)

    @Query("SELECT * FROM notes_table ORDER BY saved_time DESC")
    fun getAllFlow(): Flow<List<NoteDb>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getItemFlow(id: Long): Flow<NoteDb>
}