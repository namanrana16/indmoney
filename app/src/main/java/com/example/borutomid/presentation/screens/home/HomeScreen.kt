package com.example.borutomid.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.borutomid.navigation.Screen
import com.example.borutomid.presentation.common.ListContent
import com.example.borutomid.presentation.components.RatingWidget

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController:NavHostController,
    homeViewModel: HomeViewModel= hiltViewModel())
{
val allHeroes=homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    Scaffold(topBar = { HomeTopBar(onSearchClicked = {
        navController.navigate(Screen.Search.route)
    })

    },
        content = {
            ListContent(heroes = allHeroes, navController =navController )
        }
    )


}