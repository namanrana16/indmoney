package com.example.borutomid.presentation.common

import android.media.Rating
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.borutomid.R
import com.example.borutomid.domain.model.Hero
import com.example.borutomid.navigation.Screen
import com.example.borutomid.presentation.components.RatingWidget
import com.example.borutomid.presentation.components.ShimmerEffect
import com.example.borutomid.ui.theme.Shapes
import com.example.borutomid.ui.theme.topAppBarContent
import com.example.borutomid.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(heroes:LazyPagingItems<Hero>,navController:NavHostController)
{

    val result = handlePagingResult(heroes = heroes)
    if(result)
    {
        LazyColumn(contentPadding = PaddingValues(all=10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp))
        {
            items(items=heroes,key={hero->
                hero.id

            }){hero->
                hero?.let { HeroItem(hero = it, navController =navController ) }

            }
        }
    }




    
}




@Composable
fun handlePagingResult(heroes:LazyPagingItems<Hero>):Boolean{


    heroes.apply {
      val error= when{
          loadState.refresh is LoadState.Error-> loadState.refresh as LoadState.Error
          loadState.prepend is LoadState.Error-> loadState.prepend as LoadState.Error
          loadState.append is LoadState.Error-> loadState.append as LoadState.Error
          else -> null
      }
        return when{
            loadState.refresh is LoadState.Loading-> {
                ShimmerEffect()
            false
            }
error!=null->
{
    EmptyScreen(error = error, heroes = heroes)
    false

}
            heroes.itemCount<1->{
                EmptyScreen()
            false
            }

            else ->true
        }
    }
}









@ExperimentalCoilApi
@Composable
fun HeroItem(hero:Hero,navController: NavHostController)
{
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}")
    {
        placeholder(R.drawable.ic_logo_svg)
        error(R.drawable.ic_logo_svg)
    }

    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { navController.navigate(Screen.Details.passHeroId(heroId = hero.id)) },
         horizontalArrangement = Arrangement.Start


    ){
        Surface() {
            Image(modifier = Modifier.height(100.dp).width(80.dp).padding(all = 5.dp),painter = painter, contentDescription = "Hero Image", contentScale = ContentScale.Crop)

        }

        Spacer(modifier = Modifier.width(5.dp))

        Surface(modifier = Modifier
            .fillMaxWidth(),

            color = Color.Black.copy(alpha = ContentAlpha.medium),

            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        {

            Column(modifier= Modifier.fillMaxWidth()
                .padding(all = 7.dp)) {

                Text(text = hero.name,
                    color = MaterialTheme.colors.topAppBarContent,
                fontSize = MaterialTheme.typography.h5.fontSize ,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                )


                Text(text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize ,

                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,

                    )



            }


        }
    }
}





@ExperimentalCoilApi
@Composable
@Preview
fun HeroItemPreview()
{
    HeroItem(hero = Hero(id=1, name = "Susuke", image = "",
        about = "text to lorem ipsum",
        rating = 4.3,
        power = 9000,
        month = "May", day = "", family = listOf(),
        abilities = listOf(),
        natureTypes = listOf()
    ),
        navController = rememberNavController( ))

}