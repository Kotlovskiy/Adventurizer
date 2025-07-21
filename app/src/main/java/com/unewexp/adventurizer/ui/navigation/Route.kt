package com.unewexp.adventurizer.ui.navigation

sealed class Route(val route: String) {
    object MainScreen: Route("main screen")
    object Favorites: Route("favorites")
    object Setting: Route("setting")
}