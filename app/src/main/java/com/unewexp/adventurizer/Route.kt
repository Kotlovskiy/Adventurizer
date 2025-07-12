package com.unewexp.adventurizer

sealed class Route(val route: String) {
    object MainScreen: Route("main screen")
    object Favorites: Route("favorites")
    object Setting: Route("setting")
}