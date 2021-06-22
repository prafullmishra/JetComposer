package com.prafullm.jetcomposer.screens

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prafullm.jetcomposer.R
import com.prafullm.jetcomposer.ui.theme.droidGreen
import kotlin.math.atan
import kotlin.math.tan

const val maxAngle = 50f
val maxTranslation = 140.dp.value

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun Parallax() {

    var angle by remember { mutableStateOf(Pair(0f, 0f)) }
    var start by remember { mutableStateOf(Pair(-1f, -1f)) }
    var viewSize by remember { mutableStateOf(Size.Zero) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .onGloballyPositioned { coordinates ->
                viewSize = Size(
                    width = coordinates.size.width.toFloat(),
                    height = coordinates.size.height.toFloat()
                )
            }
            .pointerInteropFilter { m ->
                when (m.action) {
                    MotionEvent.ACTION_UP -> {
                        start = Pair(-1f, -1f)
                    }
                    MotionEvent.ACTION_DOWN -> {
                        start = Pair(m.rawX, m.rawY)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        if (viewSize != Size.Zero) {
                            val end = Pair(m.rawX, m.rawY)
                            val newAngle = getRotationAngles(start, end, viewSize)
                            var x: Float = angle.first + newAngle.first
                            var y: Float = angle.second + newAngle.second

                            if (x > maxAngle) x = maxAngle
                            else if (x < -maxAngle) x = -maxAngle

                            if (y > maxAngle) y = maxAngle
                            else if (y < -maxAngle) y = -maxAngle

                            angle = Pair(x, y)
                            start = end
                        }
                    }
                }
                true
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .size(250.dp)
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier
                    .graphicsLayer(
                        transformOrigin = TransformOrigin(0.5f, 0.5f),
                        rotationY = animateFloatAsState(-angle.first).value,
                        rotationX = animateFloatAsState(angle.second).value,
                        cameraDistance = 16.dp.value
                    )
                    .width(300.dp)
                    .aspectRatio(1f)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.parallax_n_back),
                    contentDescription = "Parallax Background",
                    modifier = Modifier.fillMaxSize()
                )
                Box(contentAlignment = Alignment.BottomStart) {
                    Text(
                        text = "</> Made with Jetpack Compose",
                        color = Color.White,
                        fontSize = 9.sp,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .alpha(0.8f)
                            .padding(start = 12.dp, bottom = 13.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .graphicsLayer(
                        transformOrigin = TransformOrigin(0.5f, 0.5f),
                        rotationY = animateFloatAsState(-angle.first).value,
                        rotationX = animateFloatAsState(angle.second).value,
                        cameraDistance = 16.dp.value,
                        translationX = -getTranslation(angle.first, maxTranslation),
                        translationY = -getTranslation(angle.second, maxTranslation)
                    )
                    .size(250.dp)
                    .align(Alignment.Center)
            ){
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.parallax_n_front), contentDescription = "Parallax Background")
            }

        }
    }
}

/**
 * This method converts the current touch input to rotation values based on the original point
 * at which the touch event started.
 *
 * @param start : coordinates of first touch event
 * @param end : coordinates of final touch event
 */
fun getRotationAngles(
    start: Pair<Float, Float>,
    end: Pair<Float, Float>,
    size: Size
): Pair<Float, Float> {
    /**
     * 1. get the magnitude of drag event, based on screen's width & height & acceleration
     * 2. get the direction/angle of the drag event
     */
    val acceleration = 3
    val distances = getDistances(end, start)
    val rotationX = (distances.first / size.width) * maxAngle * acceleration
    val rotationY = (distances.second / size.height) * maxAngle * acceleration
    return Pair(rotationX, rotationY)
}

fun getDistances(p1: Pair<Float, Float>, p2: Pair<Float, Float>): Pair<Float, Float> {
    return Pair(
        p2.first - p1.first,
        p2.second - p1.second
    )
}

fun getTranslation(angle: Float, maxDistance: Float): Float {
    return (angle/90f) * maxDistance
}

