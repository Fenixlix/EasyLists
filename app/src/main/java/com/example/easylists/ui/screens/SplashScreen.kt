package com.example.easylists.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.easylists.model.data_types.Screens
import com.example.easylists.ui.decorative_comp.SplashScreenDesign
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (navController: NavController) {
    SplashScreenDesign()
    LaunchedEffect(key1 = true){
        delay(200) // just to show the splash screen
        navController.popBackStack()
        navController.navigate(Screens.MainScreen.route)
    }
}