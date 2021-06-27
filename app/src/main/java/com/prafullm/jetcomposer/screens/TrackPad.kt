package com.prafullm.jetcomposer.screens

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prafullm.jetcomposer.R
import com.prafullm.jetcomposer.ui.theme.darkBlue
import com.prafullm.jetcomposer.ui.theme.lightBlue
import kotlin.math.max

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun TrackPad() {
    var coordinate by remember { mutableStateOf(Offset(10f, 10f)) }
    var buttonBounds by remember { mutableStateOf(Rect(Offset.Zero, Offset.Zero)) }
    var isAboveButton by remember { mutableStateOf(false) }
    var isButtonTapped by remember { mutableStateOf(false) }

    val backgroundColor = animateColorAsState(targetValue = if(isButtonTapped) lightBlue else Color.White)
    var downStartTime = 0L

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)) {
        Card(
            backgroundColor = backgroundColor.value,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            elevation = 3.dp,
            shape = RoundedCornerShape(5)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = {  },
                    shape = RoundedCornerShape(50),
                    elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = darkBlue,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    modifier = Modifier.onGloballyPositioned { c: LayoutCoordinates->
                        buttonBounds = c.boundsInParent()
                    }
                ){
                    Row(){
                        AnimatedVisibility(visible = !isButtonTapped) {
                            Text(
                                text = "Tap",
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                        AnimatedVisibility(visible = isButtonTapped) {
                            Text(
                                text = "Tapped!",
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .offset(
                            with(LocalDensity.current) { coordinate.x.toDp() },
                            with(LocalDensity.current) { coordinate.y.toDp() }
                        )
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(if(!isAboveButton) R.drawable.ic_cursor else R.drawable.ic_pointing_hand),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .pointerInteropFilter { m ->
                    when (m.action) {
                        MotionEvent.ACTION_UP -> {
                            if (System.currentTimeMillis() - downStartTime < 200L) {
                                if (isAboveButton) isButtonTapped = !isButtonTapped
                            }
                        }
                        MotionEvent.ACTION_DOWN -> {
                            downStartTime = System.currentTimeMillis()
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val eventOffset = Offset(max(0f, m.x), max(0f, m.y))
                            coordinate = eventOffset
                            isAboveButton = buttonBounds.contains(eventOffset)
                        }
                    }
                    true
                },
            elevation = 2.dp,
            shape = RoundedCornerShape(5)
        ) {}
    }
}