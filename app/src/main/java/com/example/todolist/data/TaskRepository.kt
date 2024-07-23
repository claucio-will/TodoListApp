package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun addTask(task: TaskModel) {
        taskDao.addTask(task)
    }

    fun getAllTasks(): Flow<List<TaskModel>> = taskDao.getAllTasks()

    fun getTaskById(id: Long): Flow<TaskModel> {
        return taskDao.getTaskById(id)
    }

    suspend fun updateTask(task: TaskModel) = taskDao.updateTask(task)

    suspend fun deleteTask(task: TaskModel) = taskDao.deleteTask(task)
}