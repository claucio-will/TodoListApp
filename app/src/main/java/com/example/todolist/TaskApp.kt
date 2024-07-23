package com.example.todolist

import android.app.Application
import com.example.todolist.data.Graph

class TaskApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}