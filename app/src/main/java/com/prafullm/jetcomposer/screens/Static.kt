package com.prafullm.jetcomposer.screens

import android.view.Choreographer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prafullm.jetcomposer.R
import com.prafullm.jetcomposer.ui.theme.Black950
import kotlinx.coroutines.delay
import kotlin.random.Random

val staticColors = listOf(
    Color.Black,
    Color.White,
    Color.Gray,
    Color.LightGray,
    Color.DarkGray
)

@Preview
@Composable
fun Television() {
    var isSwitchedOn by remember { mutableStateOf(false) }
    var isScreenOn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(2.dp),
            backgroundColor = Color.Black,
            modifier = Modifier
                .width(300.dp)
                .aspectRatio(1.77f)
        ) {
            Column(
                Modifier
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                    .fillMaxSize()) {
                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(1.dp),
                    backgroundColor = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ){
                    if(isSwitchedOn) {
                        LaunchedEffect(isSwitchedOn) {
                            delay(500)
                            isScreenOn = true
                        }
                    } else {
                        isScreenOn = false
                    }
                    if(isScreenOn) StaticCanvas()
                    else Surface(color = Black950, modifier = Modifier.fillMaxSize()) {}
                }

                Box(
                    modifier = Modifier
                        .height(5.dp)
                        .fillMaxWidth()
                        .padding(end = 4.dp),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.size(2.dp),
                        color = if(isSwitchedOn) Color.Green else Color.Red
                    ) {}
                }
            }
        }
        Box() {
            Canvas(modifier = Modifier.size(20.dp, 24.dp)) {
                drawRect(
                    brush = Brush.verticalGradient(
                        listOf(Color.LightGray, Color.White, Color.White)),
                    size = size
                )
            }
            Canvas(modifier = Modifier.size(20.dp, 24.dp)) {
                drawRect(
                    brush = Brush.horizontalGradient(
                        listOf(Color.LightGray, Color.Transparent, Color.LightGray)),
                    size = size
                )
            }
        }
        Canvas(modifier = Modifier.size(150.dp, 4.dp)) {
            drawRoundRect(
                cornerRadius = CornerRadius(2.dp.toPx(), 2.dp.toPx()),
                brush = Brush.verticalGradient(
                    listOf(Color.White, Color.LightGray)),
                size = size
            )
        }
        Spacer(Modifier.weight(1f))
        Button(
            enabled = true,
            onClick = { isSwitchedOn = !isSwitchedOn },
            shape = CircleShape,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 8.dp,
                pressedElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_power_settings_new_24),
                contentDescription = null,
                modifier = Modifier.padding(0.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun StaticCanvas() {
    var isRunning by remember { mutableStateOf(true) }
    val pixelSize = with(LocalDensity.current) { 3.dp.toPx() }
    var canvasMeasured by remember { mutableStateOf(false) }
    var tableSize by remember { mutableStateOf(Size.Zero) }
    var pixels by remember { mutableStateOf(listOf<Color>()) }

    Canvas(modifier = Modifier.fillMaxSize()) {

        if(!canvasMeasured) {
            canvasMeasured = true
            tableSize = Size((size.width/pixelSize)+1, (size.height/pixelSize)+1)
        }

        if(pixels.isNotEmpty()) {
            val numberOfColumns = tableSize.width.toInt()
            val numberOfRows = tableSize.height.toInt()
            var current = 0

            for(col in 0 until numberOfColumns) {
                for(row in 0 until numberOfRows) {
                    drawRect(
                        color = pixels[current],
                        topLeft = Offset(
                            x = col * pixelSize,
                            y= row * pixelSize
                        ),
                        size = Size(pixelSize, pixelSize)
                    )
                    current++
                }
            }
        }
    }

    val frameCallback = object : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
            if(tableSize != Size.Zero) {
                pixels = generatePixelColors(tableSize.width.toInt(), tableSize.height.toInt())
            }
            if(isRunning) Choreographer.getInstance().postFrameCallback(this)
        }
    }
    Choreographer.getInstance().postFrameCallback(frameCallback)

    /**
     * to clear up the frame callback
     */
    DisposableEffect(true) {
        onDispose {
            isRunning = false
            Choreographer.getInstance().removeFrameCallback(frameCallback)
        }
    }
}

private fun generatePixelColors(width: Int, height: Int): List<Color> {
    val pixels = mutableListOf<Color>()
    for(i in 0 until (width * height)) {
        pixels.add(staticColors[Random.nextInt(0, staticColors.size)])
    }
    return pixels
}

