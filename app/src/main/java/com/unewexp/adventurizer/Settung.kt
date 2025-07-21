package com.unewexp.adventurizer

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.edit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setting(
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

        val appContext = LocalContext.current
        val sharedPreferences = appContext.getSharedPreferences("app_pref", Context.MODE_PRIVATE)
        var isDarkTheme by remember { mutableStateOf(sharedPreferences.getBoolean("isDarkTheme", false)) }
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.TopStart
        ){
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Theme LIGHT/DARD")
                    OutlinedIconToggleButton(
                        isDarkTheme,
                        {
                            isDarkTheme = !isDarkTheme
                            sharedPreferences.edit(commit = true) { putBoolean("isDarkTheme", isDarkTheme) }
                        }
                    ){}
                }
            }
        }
    }
}