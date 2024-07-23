package com.example.todolist.data

import android.content.Context
import androidx.room.Room

object Graph {

  private lateinit var database: TaskDatabase


   val taskRepository by lazy {
     TaskRepository(taskDao = database.taskDao())
   }

   fun provide(context: Context){
      database = Room.databaseBuilder(context, TaskDatabase::class.java, "taskList.db").build()
   }
}