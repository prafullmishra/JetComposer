package com.prafullm.jetcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.prafullm.jetcomposer.model.Destination
import com.prafullm.jetcomposer.model.HomeItem
import com.prafullm.jetcomposer.navigation.*
import com.prafullm.jetcomposer.ui.theme.JetComposerTheme

class MainActivity : ComponentActivity() {

    private val itemList = listOf(
        HomeItem(R.string.item_parallax, R.string.item_parallax_sub, Destination.Parallax),
        HomeItem(R.string.item_tv_static, R.string.item_tv_static_sub, Destination.TvStatic),
        HomeItem(R.string.item_mac, R.string.item_mac_sub, Destination.Mac),
        HomeItem(R.string.item_trackpad, R.string.item_trackpad_sub, Destination.TrackPad),
        HomeItem(R.string.item_igchat, R.string.item_igchat_sub, Destination.IgChat),
    )

    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(true) }
            JetComposerTheme(
                darkTheme = isDarkMode
            ) {
                Surface(color = MaterialTheme.colors.background) {
                    JetComposerApp(items = itemList, onThemeSwitch = { isDarkMode = !isDarkMode })
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun JetComposerApp(items: List<HomeItem>, onThemeSwitch:() -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Home.destinationName
    ) {
        addHomeScreen(navController, items, onThemeSwitch)
        addTvStatic()
        addParallax()
        addMac()
        addTrackPad()
        addIgChat(onUpPressed = { navController.popBackStack() })
    }
}


