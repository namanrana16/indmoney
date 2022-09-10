package com.example.borutomid.presentation.screens.details


import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.borutomid.domain.model.Hero
import com.example.borutomid.presentation.components.InfoBox
import com.example.borutomid.presentation.components.OrderedList
import com.example.borutomid.ui.theme.titleColor
import com.example.borutomid.util.Constants.BASE_URL
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(navController:NavHostController,selectedHero: Hero?,colors:Map<String,String>)
{


    var vibrant by remember {
        mutableStateOf("#000000")
    }

    var darkVibrant by remember {
        mutableStateOf("#000000")
    }

    var onDarkVibrant by remember {
        mutableStateOf("#FFFFFF")
    }

    LaunchedEffect(key1 = selectedHero)
    {
//        vibrant= colors["vibrant"]!!
//        darkVibrant=colors["darkVibrant"]!!
//        onDarkVibrant=colors["onDarkVibrant"]!!

        colors["vibrant"]?.let { vibrant = it }
        colors["darkVibrant"]?.let { darkVibrant = it }
        colors["onDarkVibrant"]?.let { onDarkVibrant = it }
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color(parseColor(darkVibrant)))
    }




    val scaffoldState= rememberBottomSheetScaffoldState(bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction=scaffoldState.currentSheetFraction


    val radiusAnim by animateDpAsState(targetValue = if(currentSheetFraction==1f) 40.dp else 0.dp)


    BottomSheetScaffold(sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd =radiusAnim ),scaffoldState = scaffoldState,
        sheetPeekHeight = 140.dp,
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content ={
            selectedHero?.let {
                BackGroundContent(heroImage = it.image, imageFraction = currentSheetFraction,
                    backgroundcolor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    })
            }

        }
    )


}


@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction:Float
get(){
    val fraction=bottomSheetState.progress.fraction
    val targetValue=bottomSheetState.targetValue
    val currentValue=bottomSheetState.currentValue


    return when{
        currentValue==BottomSheetValue.Collapsed && targetValue==BottomSheetValue.Collapsed ->1f
        currentValue==BottomSheetValue.Expanded && targetValue==BottomSheetValue.Expanded ->0f
        currentValue==BottomSheetValue.Collapsed && targetValue==BottomSheetValue.Expanded->1f-fraction
        currentValue==BottomSheetValue.Expanded && targetValue==BottomSheetValue.Collapsed ->0f+fraction

        else->fraction
    }
}








@ExperimentalCoilApi
@Composable
fun BackGroundContent(heroImage:String,imageFraction: Float=1f,backgroundcolor:Color=MaterialTheme.colors.surface,
                                 onCloseClicked:()->Unit
                      ){

    val imageUrl="$BASE_URL${heroImage}"
    val painter= rememberImagePainter(imageUrl){error(com.example.borutomid.R.drawable.ic_logo)}
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundcolor))
    {
        Image(painter =painter, contentDescription ="Hero Image", contentScale = ContentScale.Crop, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = imageFraction + 0.4f)
            .align(Alignment.TopStart))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

            IconButton(onClick = {onCloseClicked()}, modifier = Modifier.padding(all=10.dp)) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White,
                modifier = Modifier.size(32.dp)
                    )
            }
            
        }
    }

}




@Composable
fun BottomSheetContent(selectedHero: Hero,infoBoxIconColor: Color = MaterialTheme.colors.primary,
                        sheetBackgroundColor:Color= MaterialTheme.colors.surface,
                       contentColor:Color= MaterialTheme.colors.titleColor

                       )
{

    Column(modifier = Modifier
        .background(sheetBackgroundColor)
        .padding(all = 20.dp)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {

            Icon(modifier= Modifier
                .size(32.dp)
                .weight(2f),
                painter = painterResource(id = com.example.borutomid.R.drawable.ic_logo_svg),
                contentDescription = "Logo",
                tint = infoBoxIconColor
            )
            Text(modifier=Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }


        Row (
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
                ){


        }

        Text(modifier = Modifier.fillMaxWidth(),
            text = "About", fontSize = MaterialTheme.typography.subtitle1.fontSize,
        color = contentColor,
        fontWeight = FontWeight.Bold )






        Text(modifier = Modifier
            .alpha(ContentAlpha.medium)
            .padding(bottom = 15.dp),
            text = selectedHero.about, fontSize = MaterialTheme.typography.body1.fontSize,
            color = contentColor,
            maxLines = 7
          )
        



    }

}





@Composable
@Preview

fun BottomSheetPreview()
{
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Naruto",
            image = "explore.png",
            about = "String",
            rating = 4.3,
            power = 93,
            month = "String",
            day = "String",
            family = listOf("Kakashi"),
            abilities = listOf("kakashi"),
            natureTypes = listOf("Kakashi")
        )
    )
}










