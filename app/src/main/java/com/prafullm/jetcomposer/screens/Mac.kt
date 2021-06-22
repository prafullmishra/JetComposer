package com.prafullm.jetcomposer.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Mac(screenContent: @Composable () -> Unit) {
    val macBezel = Color(0XFFEFF0F1)
    val macColor = Color(0XFFC5C5E9)
    val macGradientDark = Color(0XFF9F9EBE)
    val macGradientLight = Color(0XFFB8B7D6)
    val macLight = Color(0XFFD6D5F4)
    val macXtraDark = Color(0xFF6F6E8E)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = macBezel,
                shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(3.dp))
                    Canvas(Modifier.size(3.dp)) {
                        drawCircle(Color.Black)
                    }
                    Spacer(Modifier.height(3.dp))
                    Box(Modifier.padding(horizontal = 9.dp)) {
                        Card(
                            border = BorderStroke(1.dp, Color.Black),
                            backgroundColor = Color.Black,
                            shape = RoundedCornerShape(1.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.77F) // 16:9 display ratio
                        ) {
                            screenContent.invoke()
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(8.5f),
                shape = RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp),
                color = macColor) {}

            Canvas(modifier = Modifier
                .width(80.dp)
                .aspectRatio(1.7f)) {
                drawRect(
                    brush = Brush.verticalGradient(listOf(macGradientDark, macGradientLight)),
                    size = size
                )
            }
            Surface(
                color = macLight,
                modifier = Modifier.height(1.dp).width(80.dp)) {}
            Surface(
                color = macXtraDark,
                modifier = Modifier.height(0.5.dp).width(80.dp)) {}
            Surface(
                color = macColor,
                modifier = Modifier.height(4.dp).width(84.dp)) {}
        }
    }
}