package com.unewexp.adventurizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.unewexp.adventurizer.ui.theme.AdventurizerTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AdventureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdventureViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            AdventurizerTheme {
                MainScreen(
                    onGenerateClick = { viewModel.generateNewActivity() },
                    onFavoritesClick = {},
                    onSettingsClick = {}
                )
            }
        }
    }
}
