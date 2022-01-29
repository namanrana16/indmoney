package com.example.borutomid.presentation.screens.search

import android.view.Surface
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.borutomid.ui.theme.topAppBarBackground
import com.example.borutomid.ui.theme.topAppBarContent

@Composable
fun SearchTopBar(text:String,onTextChange:(String)->Unit,onSearchClicked:(String)->Unit,onCloseClicked:()->Unit)
{

    SearchWidget(text = text,
        onTextChange = onTextChange,
        onSearchClicked =onSearchClicked,
        onCloseClicked = onCloseClicked )


}


@Composable
fun SearchWidget(text:String,onTextChange:(String)->Unit,onSearchClicked:(String)->Unit,onCloseClicked:()->Unit)
{
    Surface(modifier= Modifier
        .fillMaxWidth()
        .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color=MaterialTheme.colors.topAppBarBackground
    ){

         TextField(modifier=Modifier.fillMaxWidth(),
             value =  text,
             onValueChange ={onTextChange(it)},
             placeholder={
                 Text(
                     text = "Search here...",
                     color= MaterialTheme.colors.topAppBarContent,
                     modifier = Modifier.alpha(ContentAlpha.medium)
                 )
             },
             textStyle = TextStyle(color=MaterialTheme.colors.topAppBarContent),
             singleLine =true,
             leadingIcon ={ IconButton(modifier = Modifier.alpha(alpha = ContentAlpha.medium),onClick = {}) {

                 Icon(imageVector = Icons.Default.Search, contentDescription ="Search" , tint = MaterialTheme.colors.topAppBarContent)
             }
             },
             trailingIcon = {
                 IconButton(onClick = {if(text.isNotEmpty()){
                     onTextChange("")}
                     else
                     {onCloseClicked()}
                 } ) {

                     Icon(imageVector = Icons.Default.Close,
                         contentDescription ="Close" ,
                         tint = MaterialTheme.colors.topAppBarContent)
                 }
             },
             keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
             keyboardActions = KeyboardActions(onSearch = {onSearchClicked(text)})
         ,
             colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent, cursorColor = MaterialTheme.colors.topAppBarContent)
         )


    }
}