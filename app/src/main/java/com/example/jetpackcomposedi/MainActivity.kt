package com.example.jetpackcomposedi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposedi.ui.compnent.CustomToolbar
import com.example.jetpackcomposedi.ui.detail.DetailEvent
import com.example.jetpackcomposedi.ui.detail.DetailScreen
import com.example.jetpackcomposedi.ui.detail.DetailViewModel
import com.example.jetpackcomposedi.ui.home.HomeScreen
import com.example.jetpackcomposedi.ui.home.HomeViewModel
import com.example.jetpackcomposedi.ui.theme.JetpackComposeDITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDITheme {
                val navController = rememberNavController()
                var title by remember {
                    mutableStateOf("Home")
                }

                Scaffold(
                    topBar = {
                        CustomToolbar(
                            title = title,
                            isBackIconVisible = title != "Home",
                            onBack = {
                                navController.popBackStack()
                                title = "Home"
                            }
                        )
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home_screen",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(route = "home_screen") {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            val state by homeViewModel.state.collectAsState()
                            HomeScreen(
                                uiState = state,
                                onClick = {
                                    navController.navigate("detail_screen/$it")
                                    title = "Product Details"
                                }
                            )
                        }
                        composable(route = "detail_screen/{id}") {
                            val detailViewModel = hiltViewModel<DetailViewModel>()
                            val detailState by detailViewModel.state.collectAsState()
                            val id = it.arguments?.getString("id") ?: "1"
                            DetailScreen(
                                uiState = detailState,
                                onEvent = {
                                    detailViewModel.onEvent(DetailEvent.OnGetProduct(id.toInt()))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
