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
        description = "Why do you watch new gen Mid Shounen?")

    object Second:OnBoardingPage(image = R.drawable.explore,
        title = "Explore",
        description = "Deep dive into more Mid shounen weeb")

    object Third:OnBoardingPage(image = R.drawable.fist,
        title = "Power",
        description = "See how power scaling works in a story which you write as you go")
}
