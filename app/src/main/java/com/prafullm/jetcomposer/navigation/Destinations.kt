package com.prafullm.jetcomposer.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.prafullm.jetcomposer.model.Destination
import com.prafullm.jetcomposer.model.HomeItem
import com.prafullm.jetcomposer.screens.*
import com.prafullm.jetcomposer.utils.composable

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

fun NavGraphBuilder.addIgChat(onUpPressed:() -> Unit) {
    composable(route = Destination.IgChat) { IgChatScreen(onUpPressed) }
}

fun NavController.navigate(destination: Destination) {
    navigate(destination.destinationName)
}