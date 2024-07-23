package com.example.todolist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.todolist.data.Graph
import com.example.todolist.data.TaskRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository = Graph.taskRepository) :
    ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")

    fun titleTaskChanged(newString: String) {
        title = newString
    }

    fun descriptionTaskChanged(newString: String) {
        description = newString
    }

    lateinit var getAllTasks: Flow<List<TaskModel>>

    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getAllTasks()
        }
    }

    fun addTask(task: TaskModel){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task)
        }
    }
    fun getTaskById(id: Long) : Flow<TaskModel>{
        return taskRepository.getTaskById(id)
    }

    fun updateTask(task: TaskModel){
        viewModelScope.launch (Dispatchers.IO){
            taskRepository.updateTask(task)
        }
    }

    fun deleteWish(task: TaskModel){
        viewModelScope.launch (Dispatchers.IO){
            taskRepository.deleteTask(task)
        }
    }


}