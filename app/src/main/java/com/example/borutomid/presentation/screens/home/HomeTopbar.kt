package com.example.borutomid.presentation.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.borutomid.ui.theme.topAppBarBackground
import com.example.borutomid.ui.theme.topAppBarContent


@Composable
fun HomeTopBar(onSearchClicked:()->Unit)
{
    TopAppBar(
        title = { Text(text = "Explore", color = MaterialTheme.colors.topAppBarContent)},
        backgroundColor = MaterialTheme.colors.topAppBarBackground,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon", tint = MaterialTheme.colors.topAppBarContent)
                
            }

        }
    )


}