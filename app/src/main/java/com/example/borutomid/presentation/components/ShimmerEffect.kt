package com.example.borutomid.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.borutomid.ui.theme.ShimmerDarkGray
import com.example.borutomid.ui.theme.ShimmerLightGray
import com.example.borutomid.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect()
{

    LazyColumn(contentPadding = PaddingValues(all = 10.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp))
    {
        items(count = 2){
            AnimatedShimmerItem()
        }
    }
}


@Composable
fun AnimatedShimmerItem()
{
    val transition = rememberInfiniteTransition()
    val alphaAnim=transition.animateFloat(initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500,
                easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse)
    )
    
    ShimmerItem(alpha = alphaAnim.value)
}




@Composable
fun ShimmerItem(alpha:Float)
{
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        color=if(isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Column(modifier = Modifier.padding(all=16.dp), verticalArrangement = Arrangement.Bottom) {

            Surface(modifier = Modifier
                .alpha(alpha = alpha)
                .fillMaxWidth(0.5f)
                .height(30.dp), color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
            shape = RoundedCornerShape(size = 10.dp)){ }

            Spacer(modifier = Modifier.padding(all=10.dp))

            repeat(3)
            {
                Surface(modifier = Modifier
                    .alpha(alpha = alpha)
                    .fillMaxWidth()
                    .height(15.dp), color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = 10.dp)){ }
                Spacer(modifier = Modifier.padding(all=5.dp))
            }
            
            Row(modifier=Modifier.fillMaxWidth()) {
                repeat(5)
                {  Surface(modifier = Modifier
                    .alpha(alpha = alpha)
                    .size(20.dp), color = if(isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = 10.dp)){ }
                    Spacer(modifier = Modifier.padding(all=5.dp))
                }
            }

        }
    }
}