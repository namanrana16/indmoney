package com.example.borutomid.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.borutomid.R
import com.example.borutomid.domain.model.Hero
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.lang.Error
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error:LoadState.Error?=null,heroes:LazyPagingItems<Hero>?=null)
{
var message by remember{
    mutableStateOf("Find your Hero")
}
    var icon by remember {
         mutableStateOf(R.drawable.ic_search_document)
    }

    if(error!=null)
    {
        message= parseErrorMessage(message = error)
        icon=R.drawable.ic_network_error
    }


    var startAnimation by remember{
        mutableStateOf(false)
    }

    val alphaAnim by animateFloatAsState(targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
    animationSpec = tween(durationMillis = 1000))

    LaunchedEffect(key1 = true){startAnimation=true}

    var isRefreshing by remember{
        mutableStateOf(false)
    }

SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing) ,
    onRefresh = {
        isRefreshing=true
        heroes?.refresh()
        isRefreshing=false

    },
    swipeEnabled = error!=null
) 
{


    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =   Arrangement.Center) {

        Icon(modifier = Modifier
            .size(120.dp)
            .alpha(alpha = alphaAnim), painter = painterResource(id = icon), contentDescription ="Network Error Icon" ,
            tint = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Text(text = message, modifier = Modifier
            .padding(top = 10.dp)
            .alpha(alpha = alphaAnim),
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize)
    }
    
}

}

fun parseErrorMessage(message:LoadState.Error):String
{

    return when(message.error){
       is SocketTimeoutException ->{"Server Unavailable"}
        is ConnectException ->{"Internet Unavailable"}
        else-> "Unknown Error"
    }
}