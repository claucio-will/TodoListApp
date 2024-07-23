@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.minhalista.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.R
import com.example.todolist.ui.theme.TodoListTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppbarView(
    onBackNavClicked: () -> Unit,
    title: String

) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.app_bar_color),
            titleContentColor = colorResource(id = R.color.white),
        ),
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white)
            )
        },
        navigationIcon = {
            if (!title.contains("Minha Lista")) {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {
//        AppbarView(title = "") {
//        }
        AppbarView(onBackNavClicked = {}, title = "")
    }
}