package com.example.borutomid.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val StarColor = Color(0xFFFFC94D)
val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val ShimmerLightGray= Color(0xFFF1F1F1)
val ShimmerMediumGray= Color(0xFFE3E3E3)
val ShimmerDarkGray= Color(0xFF1D1D1D)

val Colors.welcomeScreenBackgroundColor
@Composable
get()=if(isLight) Color.White else Color.Black

val Colors.titleColor
@Composable
get()=if(isLight) DarkGray else LightGray


val Colors.descriptionColor
    @Composable
    get()=if(isLight) DarkGray.copy(alpha = 0.5f) else LightGray.copy(alpha = 0.5f)

val Colors.activeColor
    @Composable
    get()=if(isLight) Purple500 else Purple700


val Colors.inactiveColor
    @Composable
    get()=if(isLight) LightGray else DarkGray



val Colors.buttonbgColor
    @Composable
    get()=if(isLight) Purple500 else Purple700

val Colors.topAppBarContent:Color
@Composable
get()=if(isLight)Color.White else LightGray

val Colors.topAppBarBackground:Color
    @Composable
    get()=if(isLight) Purple500 else Color.Black
