package com.example.borutomid.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.borutomid.domain.model.OnBoardingPage
import com.example.borutomid.navigation.Screen
import com.example.borutomid.ui.theme.*
import com.google.accompanist.pager.*
import dagger.hilt.android.lifecycle.HiltViewModel

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navController: NavHostController,
     welcomeViewModel: WelcomeViewModel= hiltViewModel()){

    val pages = listOf(OnBoardingPage.First,OnBoardingPage.Second,OnBoardingPage.Third)

    val pagerState= rememberPagerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {

        HorizontalPager(
            modifier= Modifier
                .weight(10f)
                .align(Alignment.CenterHorizontally),
            state = pagerState,
            count = 3,
            verticalAlignment = Alignment.Top

        ) {position->
        PagerScreen(onBoardingPage = pages[position])
        }


        HorizontalPagerIndicator( modifier= Modifier
            .weight(1f)
            .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor =MaterialTheme.colors.activeColor,
            inactiveColor = MaterialTheme.colors.inactiveColor,
            indicatorWidth = 12.dp,
            spacing = 8.dp,


            )
        FinishScreen(
            modifier=Modifier.weight(1f),
            pagerState = pagerState) {

            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
            
        }
        

        
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){

    Column(modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
        ) {

        Image(
            modifier= Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription ="On Boarding Image" )

        Text(
            modifier= Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = onBoardingPage.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
            )

        Text(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 10.dp),
            text = onBoardingPage.description,
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}



@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishScreen(
    modifier: Modifier,
    pagerState:PagerState,
    onClick:()-> Unit
)
{
        Row(modifier = Modifier.padding(40.dp),
        verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
            )
        {
                       AnimatedVisibility(
                           modifier=Modifier.fillMaxWidth(),
                           visible =pagerState.currentPage==2) {
                           Button(onClick = onClick,
                           colors = ButtonDefaults.buttonColors(
                               backgroundColor =MaterialTheme.colors.buttonbgColor
                                   , contentColor = Color.White
                           )
                               ) {

                               Text(text = "Finish")
                           }
                           
                       }
        }
}














@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview(){
    
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview(){

    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview(){

    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

















