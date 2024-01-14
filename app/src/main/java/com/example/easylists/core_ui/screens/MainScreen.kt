package com.example.easylists.core_ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easylists.core_model.data_types.Screens
import com.example.easylists.core_ui.interactive_comp.ScreenCard

@Composable
fun MainScreen(navController: NavController) {

    // Drop the first 2 items of the Screen enum list
    // that correspond to the splash and the main screen
    val screenList = Screens.values().drop(2)

    Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
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