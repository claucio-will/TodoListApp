package com.example.minhalista.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.minhalista.component.AppbarView

import com.example.todolist.R
import com.example.todolist.data.TaskModel
import com.example.todolist.viewmodel.TaskViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: TaskViewModel,
    navController: NavHostController
) {
    var snackMessage by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (id != 0L) {
        val task = viewModel.getTaskById(id).collectAsState(initial = TaskModel(0L, "", ""))
        viewModel.title = task.value.title
        viewModel.description = task.value.description
    } else {
        viewModel.title = ""
        viewModel.description = ""
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppbarView(onBackNavClicked = {
                navController.navigateUp()
            }, title = if (id != 0L) "Atualizar Tarefa" else "Criar nova Tarefa")
        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp),
                value = viewModel.title,
                onValueChange = { viewModel.titleTaskChanged(it) },
                label = { Text(text = "Titulo") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp),
                value = viewModel.description,
                onValueChange = { viewModel.descriptionTaskChanged(it) },
                label = { Text(text = "Descrição") },

                )
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                onClick = {
                    if (viewModel.title.isNotEmpty() && viewModel.description.isNotEmpty()) {
                        if (id != 0L) {
                            //Update Tasks
                            viewModel.updateTask(
                                task = TaskModel(
                                    id = id,
                                    title = viewModel.title,
                                    description = viewModel.description
                                )
                            )
                        } else {
                            //Add Task
                            viewModel.addTask(
                                TaskModel(
                                    title = viewModel.title.trim(),
                                    description = viewModel.description.trim()
                                )
                            )
                           // snackMessage = "Wish has been created"
                        }
                    } else {
                        //snackMessage = "enter fileds to create a wish"
                    }
                    scope.launch {
                       // snackbarHostState.showSnackbar(snackMessage)
                        navController.navigateUp()
                    }
                }
            ) {
                Text(
                    text = if (id != 0L) "ATUALIZAR" else "SALVAR",
                    color = colorResource(id = R.color.app_bar_color)
                )
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
private fun CreateNewWishPreview() {
    // AddEditDetailView(navController = rememberNavController())
}