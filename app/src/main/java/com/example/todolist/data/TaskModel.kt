package com.example.todolist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task-table")
data class TaskModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "wish-title")
    val title: String = "",

    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)