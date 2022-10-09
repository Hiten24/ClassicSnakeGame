package com.example.classicsnakegame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.classicsnakegame.NUMBER_IN_ROW
import com.example.classicsnakegame.SQUARE_CORNER_RADIUS_SIZE

const val BOARD_PIXEL_TYPE = "board pixel"
const val SNAKE_PIXEL_TYPE = "snake pixel"
const val FOOD_PIXEL_TYPE = "food pixel"

@Composable
fun Pixel(type: String) {
    val config = LocalConfiguration.current
    val color = when (type) {
        SNAKE_PIXEL_TYPE -> Color.White
        FOOD_PIXEL_TYPE -> Color.Red
        else -> MaterialTheme.colors.surface
    }
    val size = config.screenHeightDp / (NUMBER_IN_ROW + NUMBER_IN_ROW + 16)
    val shape = if (type == FOOD_PIXEL_TYPE) CircleShape else RoundedCornerShape(SQUARE_CORNER_RADIUS_SIZE)
    Box(modifier = Modifier
        .size(size.dp)
        .background(color, shape)
    )
}