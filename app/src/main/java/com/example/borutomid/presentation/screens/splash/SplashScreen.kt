package com.example.borutomid.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutomid.R
import com.example.borutomid.navigation.Screen
import com.example.borutomid.ui.theme.Purple500
import com.example.borutomid.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController,
splashViewModel: SplashViewModel= hiltViewModel())
{

    val onBoardingCompleted by splashViewModel.
    onBoardingCompleted.collectAsState()

    val degrees = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true)
    {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if(onBoardingCompleted)
        {
            navController.navigate(Screen.Home.route)
        }else
            navController.navigate(Screen.Welcome.route)

    }
    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees:Float){

    if(isSystemInDarkTheme())
    {
        Box(modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Image(
                modifier= Modifier.rotate(degrees = degrees ),
                painter = painterResource(id = R.drawable.ic_logo_svg), contentDescription = "App Logo")
        }



    }

    else
    {
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(Purple700, Purple500)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Image(
                modifier= Modifier.rotate(degrees = degrees ),
                painter = painterResource(id = R.drawable.ic_logo_svg), contentDescription = "App Logo")
        }
    }


}


@Composable
@Preview
fun SplashScreenPreview(){
    Splash(degrees = 0f)
}