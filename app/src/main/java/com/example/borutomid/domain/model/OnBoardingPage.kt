package com.example.borutomid.domain.model

import androidx.annotation.DrawableRes
import com.example.borutomid.R

sealed class OnBoardingPage(
    @DrawableRes
    val image:Int,
    val title:String,
    val description:String
)
{

    object First:OnBoardingPage(image = R.drawable.welcome,
        title = "Welcome",
        description = "Why do you watch new gen  Shounen?")

    object Second:OnBoardingPage(image = R.drawable.explore,
        title = "Explore",
        description = "Deep dive into more Shounen ")

    object Third:OnBoardingPage(image = R.drawable.fist,
        title = "Power",
        description = "Power Scaling across 2 Gens")
}
