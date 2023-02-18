package com.emikhalets.simplenotes.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emikhalets.simplenotes.data.database.entities.TaskDb

@Database(
    entities = [TaskDb::class],
    autoMigrations = [
        AutoMigration(1, 2)
    ],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract val tasksDao: TasksDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "SimpleNotes.db").build()
    }
}