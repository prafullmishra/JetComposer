package com.prafullm.jetcomposer.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.composable
import com.prafullm.jetcomposer.model.Destination
import com.prafullm.jetcomposer.model.HomeItem
import com.prafullm.jetcomposer.screens.*

@ExperimentalMaterialApi
fun NavGraphBuilder.addHomeScreen(
    navController: NavController,
    items: List<HomeItem>,
    onThemeSwitch:()-> Unit
) {
    composable(route = Destination.Home) {
        HomeScreen(
            screens = items,
            onItemClick = { screen -> navController.navigate(screen) },
            onThemeSwitch = onThemeSwitch
        )
    }
}

fun NavGraphBuilder.addTvStatic() {
    composable(route = Destination.TvStatic) { Television() }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.addParallax() {
    composable(route = Destination.Parallax) { Parallax() }
}

fun NavGraphBuilder.addMac() {
    composable(route = Destination.Mac) { Mac({}) }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.addTrackPad() {
    composable(route = Destination.TrackPad) { TrackPad() }
}

/*
 * Utility wrapper ext function for NavGraphBuilder.compose()
 */
fun NavGraphBuilder.composable(
    route: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(route.destinationName, arguments, deepLinks, content)
}

fun NavController.navigate(destination: Destination) {
    navigate(destination.destinationName)
}