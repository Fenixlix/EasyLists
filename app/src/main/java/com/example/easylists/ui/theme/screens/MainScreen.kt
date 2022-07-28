package com.example.easylists.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.easylists.R
import com.example.easylists.model.Screens
import com.example.easylists.ui.theme.interactive_comp.ScreenCard

// The main screen of the app, with cards for select which type of list to use
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {

    val screenList = listOf(Screens.ShopList, Screens.ItemList, Screens.TodoList)
    Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            items(screenList) {
                ScreenCard(
                    title = it.title,
                    painter = painterResource(id = R.drawable.app_easy_lists_ico),
                    contentDescription = it.title
                ) {
                    navController.navigate(it.route)
                }
            }
        }
    }
}