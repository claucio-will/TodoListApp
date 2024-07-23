package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TaskDao {

    @Insert
    abstract suspend fun addTask(task: TaskModel)

    @Query("SELECT * FROM `task-table`")
    abstract fun getAllTasks(): Flow<List<TaskModel>>

    @Update
    abstract suspend fun updateTask(taskEntity: TaskModel)

    @Delete
    abstract suspend fun deleteTask(taskEntity: TaskModel)

    @Query("SELECT * FROM `task-table` WHERE id =:id ")
    abstract fun getTaskById(id: Long): Flow<TaskModel>
}