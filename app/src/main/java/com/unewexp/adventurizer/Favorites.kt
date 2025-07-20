package com.unewexp.adventurizer

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unewexp.adventurizer.DB.AppDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorites(
    onBackPress: () -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.favourites)) },
                actions = {
                    Button(
                        onClick = onBackPress
                    ) {
                        Text("Назад")
                    }
                }
            )
        }
    ){ innerPadding ->

        val appContext = LocalContext.current.applicationContext
        val viewModel: AdventureViewModel = viewModel(
            factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val db = AppDatabase.getInstance(appContext as Application)
                    return AdventureViewModel(db.activityDao()) as T
                }
            }
        )

        val allFavorites = viewModel._allFavourites.collectAsStateWithLifecycle(initialValue = emptyList()).value

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            items(allFavorites) { item ->
                ItemView(item.title, { viewModel.deleteFavorite(item.serverId) })
            }
        }
    }
}

@Composable
fun ItemView(title: String, onDeletePress: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        IconButton({ onDeletePress() }){
            Icon(Icons.Default.Delete, "удалить из избранного")
        }
    }
}