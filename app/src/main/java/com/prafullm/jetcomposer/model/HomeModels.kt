package com.prafullm.jetcomposer.model

import androidx.annotation.StringRes

/**
 * Represents each item shown in the list of home screen
 */
data class HomeItem(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    val destination: Destination
)

/**
 * Each represent a destination for the Navigation Component
 */
sealed class Destination(val destinationName: String) {
    object Home: Destination("home")
    object TvStatic: Destination("tv_static")
    object Parallax: Destination("parallax")
    object Mac: Destination("imac")
    object TrackPad: Destination("trackpad")
    object IgChat: Destination("ig_chat")
}