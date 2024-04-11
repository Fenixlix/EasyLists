package com.example.easylists.core_ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.easylists.core_ui.decorative_comp.SplashScreenDesign
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (onFinish : () -> Unit) {
    SplashScreenDesign()
    LaunchedEffect(key1 = true){
        delay(200) // just to show the splash screen
        onFinish()
    }
}