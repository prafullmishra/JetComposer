package com.prafullm.jetcomposer.utils

import android.animation.ArgbEvaluator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.composable
import com.prafullm.jetcomposer.model.Destination

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

fun getColorAtProgress(progress: Float, start: Color, end: Color, evaluator: ArgbEvaluator): Color {
    return Color(evaluator.evaluate(progress, start.toArgb(), end.toArgb()) as Int)
}