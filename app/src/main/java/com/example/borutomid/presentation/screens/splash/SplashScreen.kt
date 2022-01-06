package com.example.borutomid.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.borutomid.R
import com.example.borutomid.navigation.Screen
import com.example.borutomid.ui.theme.Purple500
import com.example.borutomid.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController)
{
    Splash()
}

@Composable
fun Splash(){

    if(isSystemInDarkTheme())
    {
        Box(modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Image(painter = painterResource(id = R.drawable.ic_logo_svg), contentDescription = "App Logo")
        }



    }

    else
    {
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Image(painter = painterResource(id = R.drawable.ic_logo_svg), contentDescription = "App Logo")
        }
    }


}


@Composable
@Preview
fun SplashScreenPreview(){
    Splash()
}