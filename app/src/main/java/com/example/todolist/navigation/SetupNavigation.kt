package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minhalista.view.AddEditDetailView
import com.example.minhalista.view.HomeView
import com.example.todolist.viewmodel.TaskViewModel

@Composable
fun SetupNavigation(
    viewModel: TaskViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.HomeView.route) {
        composable(Screen.HomeView.route) {
            HomeView(navController = navController, viewModel)
        }
        composable(Screen.CreateTaskView.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
        )) {
            val id = if (it.arguments != null) it.arguments!!.getLong("id") else 0L
            AddEditDetailView(
                id = id,
                viewModel = viewModel,
                navController = navController
            )
        }
    }

}