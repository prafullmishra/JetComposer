package com.prafullm.jetcomposer.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prafullm.jetcomposer.R
import com.prafullm.jetcomposer.model.Destination
import com.prafullm.jetcomposer.model.HomeItem

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    screens: List<HomeItem>,
    onItemClick:(destination: Destination) -> Unit,
    onThemeSwitch: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)) {
        HomeHeader(onThemeSwitch)
        HomeList(
            modifier = Modifier.weight(1f),
            screens = screens,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun HomeHeader(onThemeSwitch:() -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 24.dp, top = 24.dp)) {
        Text(
            text = "Jet Composer",
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .weight(1f)
                .padding(start = 24.dp, end = 16.dp)
        )
        val icon = if (MaterialTheme.colors.isLight) {
            ImageVector.vectorResource(id = R.drawable.ic_round_wb_sunny_24)
        } else {
            ImageVector.vectorResource(id = R.drawable.ic_round_nightlight_24)
        }
        IconButton(
            onClick = onThemeSwitch
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.rotate(-45f)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeList(modifier: Modifier, screens: List<HomeItem>, onItemClick: (destination: Destination) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxWidth()) {
        items(screens) { screen ->
            HomeListItem(screen = screen, onItemClick = onItemClick)
        }
        item {
            HomeListFooter()
        }
    }
}

@Composable
fun HomeListFooter() {
    Text(
        text = "Created & maintained by @NotYourPM",
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(0.5f)
            .padding(vertical = 16.dp)
    )
}

@ExperimentalMaterialApi
@Composable
fun HomeListItem(screen: HomeItem, onItemClick: (destination: Destination) -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onItemClick(screen.destination) }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = screen.title),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = screen.subtitle),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.secondary
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_chevron_right_24),
                contentDescription = "",
                tint = MaterialTheme.colors.onSurface
            )
        }

    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
@Preview
fun ListItemPreview() {
    HomeListItem(
        screen = HomeItem(
            title = R.string.item_tv_static,
            subtitle = R.string.item_tv_static_sub,
            destination = Destination.TvStatic
        ),
        onItemClick = {}
    )
}