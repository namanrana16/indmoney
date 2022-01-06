package com.example.borutomid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ControlledComposition
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.borutomid.navigation.SetupNavGraph
import com.example.borutomid.ui.theme.BorutoMidTheme
import dagger.hilt.android.AndroidEntryPoint
import java.security.AccessController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BorutoMidTheme {

                
                navController= rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

