package com.example.todolist.navigation

sealed class Screen(val route: String) {
    object HomeView : Screen("home_view")
    object CreateTaskView : Screen("create_task")
}