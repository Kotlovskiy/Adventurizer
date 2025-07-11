package com.unewexp.adventurizer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unewexp.adventurizer.ui.theme.AdventurizerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onGenerateClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onSettingsClick: () -> Unit
){
    val viewModel: AdventureViewModel = viewModel()
    val currentActivity by viewModel.currentActivity.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Settings, "Настройки")
                    }
                })
                 },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onGenerateClick,
                modifier = Modifier.padding(bottom = 20.dp)
            ){
                Icon(Icons.Default.Refresh, "Новая активность")
            }
        },
        bottomBar = {
            BottomAppBar{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    IconButton(
                        onClick = onFavoritesClick
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Избранное")
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ){
            currentActivity?.let {
                ActivityCard(
                    activity = currentActivity!!,
                    onLike = { viewModel.addToFavorites() },
                    onDisLike = { viewModel.generateNewActivity() },
                    onSwipeUp = { viewModel.showNextActivity() },
                    onSwipeDown = { viewModel.showPrevActivity() }
                )
            } ?: Text("Нажмите на кнопку, чтобы начать!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AdventurizerTheme {
        MainScreen(
            onGenerateClick = {},
            onFavoritesClick = {},
            onSettingsClick = {}
        )
    }
}