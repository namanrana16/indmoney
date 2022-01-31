package com.example.borutomid.navigation

import android.window.SplashScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.borutomid.presentation.screens.details.DetailsScreen
import com.example.borutomid.presentation.screens.home.HomeScreen
import com.example.borutomid.presentation.screens.search.SearchScreen
import com.example.borutomid.presentation.screens.splash.SplashScreen
import com.example.borutomid.presentation.screens.welcome.WelcomeScreen
import com.example.borutomid.util.Constants.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
@ExperimentalMaterialApi
fun SetupNavGraph(navController: NavHostController){

    NavHost(navController = navController,
    startDestination =Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
        SplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Home.route){

            HomeScreen(navController = navController)
        }

        composable(route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){
                type= NavType.IntType
            })
        ){

            DetailsScreen(navController = navController)
        }

        composable(route = Screen.Search.route){

            SearchScreen(navController = navController)
        }





    }

}