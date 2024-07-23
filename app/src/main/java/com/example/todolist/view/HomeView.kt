package com.example.minhalista.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.minhalista.component.AppbarView
import com.example.minhalista.component.WishItem
import com.example.minhalista.navigationConfig.Screen
import com.example.minhalista.viewmodel.WishViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavHostController, viewModel: WishViewModel = viewModel()) {
    Scaffold(
        topBar = {
            AppbarView({}, title = "Minha Lista")

        }, floatingActionButton = {
            ExtendedFloatingActionButton(
                contentColor = Color.Gray,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                onClick = {
                    //Nova tarefa, passar o id como zero porque é a primeira
                    navController.navigate(Screen.CreateWishView.route + "/0L")

                },
                icon = { Icon(Icons.Filled.Add, "Criar nova Tarefa") },
                text = { Text(text = "Criar") },
            )
        }) {
        //Pegando a lista para exibir no card
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {
            items(wishList.value, key = { wish -> wish.id }) { wish ->
                val swipeDismissState = rememberSwipeToDismissBoxState()
                LaunchedEffect(swipeDismissState.currentValue) {
                    if (swipeDismissState.currentValue == SwipeToDismissBoxValue.StartToEnd
                        || swipeDismissState.currentValue == SwipeToDismissBoxValue.EndToStart
                    ) {
                        viewModel.deleteWish(wish)
                    }
                }
                SwipeToDismissBox(
                    state = swipeDismissState,
                    backgroundContent = {
                        when (swipeDismissState.dismissDirection) {
                            SwipeToDismissBoxValue.EndToStart -> {
                                Box(
                                    contentAlignment = Alignment.CenterEnd,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Red)
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                            SwipeToDismissBoxValue.StartToEnd -> {}
                            SwipeToDismissBoxValue.Settled -> {}
                        }
                    },
                    content = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            //Navegar para proxima tela com os dados já preenchidos
                            navController.navigate(Screen.CreateWishView.route + "/$id")
                        }
                    },
                    enableDismissFromStartToEnd = false,
                    enableDismissFromEndToStart = true

                )

            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview() {
    HomeView(navController = rememberNavController())

}
