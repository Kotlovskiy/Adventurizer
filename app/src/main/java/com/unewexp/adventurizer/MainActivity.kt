package com.unewexp.adventurizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unewexp.adventurizer.data.local.AppDatabase
import com.unewexp.adventurizer.ui.navigation.Route
import com.unewexp.adventurizer.ui.screens.Favorites
import com.unewexp.adventurizer.ui.screens.MainScreen
import com.unewexp.adventurizer.ui.screens.Setting
import com.unewexp.adventurizer.ui.theme.AdventurizerTheme
import com.unewexp.adventurizer.viewmodel.AdventureViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AdventureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getInstance(application)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AdventureViewModel(db.activityDao()) as T
            }
        }
        ).get(AdventureViewModel::class.java)
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
