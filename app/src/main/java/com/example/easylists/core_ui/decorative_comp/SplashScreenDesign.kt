package com.example.easylists.core_ui.decorative_comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.easylists.R

@Composable
fun SplashScreenDesign() {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .padding(30.dp),
            painter = painterResource(id = R.drawable.splash_screen_icon),
            contentDescription = R.string.splash_screen_description.toString()
        )
    }
}

@Preview
@Composable
private fun Prev() {
    SplashScreenDesign()
}
