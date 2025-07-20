package com.unewexp.adventurizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unewexp.adventurizer.ui.theme.AdventurizerTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AdventureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdventureViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AdventurizerTheme {
                NavHost(navController = navController, startDestination = Route.MainScreen.route){
                    composable(Route.MainScreen.route) {
                        MainScreen(
                            onNavigateToFavorites = { navController.navigate(Route.Favorites.route) },
                            onNavigateToSettings = { navController.navigate(Route.Setting.route) }
                        )
                    }
                    composable(Route.Favorites.route) {
                        Favorites(
                            onBackPress = { navController.popBackStack() }
                        )
                    }
                    composable(Route.Setting.route) {
                        Setting(
                            onBackPress = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
