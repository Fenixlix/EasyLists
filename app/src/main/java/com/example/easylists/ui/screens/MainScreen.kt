package com.example.easylists.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easylists.model.data_types.Screens
import com.example.easylists.ui.interactive_comp.ScreenCard

// The main screen of the app, with cards for select which type of list to use
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController) {

    val screenList = Screens.values().drop(1)

    Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(screenList) {
                ScreenCard(
                    title = it.title,
                    painter = painterResource(id = it.icon),
                    contentDescription = it.title
                ) {
                    navController.navigate(it.route)
                }
            }
        }
    }
}