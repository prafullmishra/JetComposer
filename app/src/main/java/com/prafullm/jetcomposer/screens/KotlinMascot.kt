package com.prafullm.jetcomposer.screens

import android.view.MotionEvent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prafullm.jetcomposer.R

private const val maxAngle = 50f
private val maxTranslation = 140.dp.value
private val mascotAssets = listOf<Int>(
    R.drawable.kotlin_mascot_2d_1,
    R.drawable.kotlin_mascot_2d_2,
    R.drawable.kotlin_mascot_2d_3,
    R.drawable.kotlin_mascot_2d_4,
    R.drawable.kotlin_mascot_2d_5,
    R.drawable.kotlin_mascot_2d_6,
    R.drawable.kotlin_mascot_2d_7,
    R.drawable.kotlin_mascot_2d_8,
    R.drawable.kotlin_mascot_2d_9,
    R.drawable.kotlin_mascot_2d_10,
    R.drawable.kotlin_mascot_2d_11,
    R.drawable.kotlin_mascot_2d_12,
    R.drawable.kotlin_mascot_2d_13,
    R.drawable.kotlin_mascot_2d_14
)

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
private fun KotlinMascot() {

    var angle by remember { mutableStateOf(Pair(0f, 0f)) }
    var start by remember { mutableStateOf(Pair(-1f, -1f)) }
    var viewSize by remember { mutableStateOf(Size.Zero) }
    val (currentAssetIndex, setCurrentAssetIndex) = remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
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
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(250.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    backgroundColor = Color.Gray,
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        //Color(0xFF7F52FF),
                                        Color.LightGray,
                                        Color(0xFFC711E1),
                                        Color(0xFFE44857),
                                        Color(0xFF7F52FF)
                                    ).reversed(),
                                    startY = 1.0f
                                )
                            )
                    ) {

                        Image(
                            imageVector = ImageVector.vectorResource(
                                id = mascotAssets.get(currentAssetIndex)
                            ),
                            contentDescription = "Parallax Background",
                            modifier = Modifier.fillMaxSize(),
                            alpha = 0.4f,
                            colorFilter = ColorFilter.tint(Color.Black)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.kotlin_logo),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = (-16).dp, y = (-16).dp)
                                .alpha(0.4f),
                            tint = Color.Black,
                        )

                        Text(
                            text = "Made with Jetpack Compose",
                            color = Color.Black,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 16.dp, top = 8.dp)
                                .alpha(0.4f)
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
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(
                            id = mascotAssets.get(currentAssetIndex)
                        ),
                        contentDescription = "Parallax foreground",
                        modifier = Modifier.offset(x = (-8).dp, y = (-8).dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.kotlin_logo),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-20).dp, y = (-20).dp),
                        tint = Color.Unspecified
                    )

                    Text(
                        text = "Made with Jetpack Compose",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp, top = 8.dp)
                            .offset(x = (-4).dp, y = (-4).dp)
                    )
                }

            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
                .align(Alignment.BottomCenter)
                .padding(8.dp)
                .shadow(8.dp, shape = RoundedCornerShape(20.dp))
        ) {
            TextButton(onClick = {
                if (currentAssetIndex == 0) {
                    setCurrentAssetIndex(mascotAssets.lastIndex)
                } else {
                    setCurrentAssetIndex(currentAssetIndex - 1)
                }
            }) {
                Text(
                    text = "<",
                    color = Color.White
                )
            }

            Text(
                text = "${currentAssetIndex + 1}/${mascotAssets.lastIndex}",
                color = Color.White
            )

            TextButton(onClick = {
                if (currentAssetIndex == mascotAssets.lastIndex) {
                    setCurrentAssetIndex(0)
                } else {
                    setCurrentAssetIndex(currentAssetIndex + 1)
                }
            }) {
                Text(
                    text = ">",
                    color = Color.White
                )
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
private fun getRotationAngles(
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

private fun getDistances(p1: Pair<Float, Float>, p2: Pair<Float, Float>): Pair<Float, Float> {
    return Pair(
        p2.first - p1.first,
        p2.second - p1.second
    )
}

private fun getTranslation(angle: Float, maxDistance: Float): Float {
    return (angle / 90f) * maxDistance
}

