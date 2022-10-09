package com.example.classicsnakegame.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.classicsnakegame.SnakeDirection

@Composable
fun Controller(onDirectionChange: (Int) -> Unit) {
    val currentDirection = remember { mutableStateOf(SnakeDirection.Down) }
    Box(modifier = Modifier.size(140.dp)) {
        ControllerButton(rotateDegree = 0f, modifier = Modifier.align(Alignment.TopCenter)) {
            if (currentDirection.value != SnakeDirection.Down) {
                onDirectionChange.invoke(it)
                currentDirection.value = SnakeDirection.Up
            }
        }
        ControllerButton(rotateDegree = 90f, modifier = Modifier.align(Alignment.CenterEnd)) {
            if (currentDirection.value != SnakeDirection.Left) {
                onDirectionChange.invoke(it)
                currentDirection.value = SnakeDirection.Right
            }
        }
        ControllerButton(rotateDegree = 180f, modifier = Modifier.align(Alignment.BottomCenter)) {
            if (currentDirection.value != SnakeDirection.Up) {
                onDirectionChange.invoke(it)
                currentDirection.value = SnakeDirection.Down
            }
        }
        ControllerButton(rotateDegree = 270f, modifier = Modifier.align(Alignment.CenterStart)) {
            if (currentDirection.value != SnakeDirection.Right) {
                onDirectionChange.invoke(it)
                currentDirection.value = SnakeDirection.Left
            }
        }
    }
}

@Composable
fun ControllerButton(rotateDegree: Float, modifier: Modifier, onClick: (Int) -> Unit) {
    IconButton(
        onClick = {
            val direction = when (rotateDegree) {
                90f -> SnakeDirection.Right
                180f -> SnakeDirection.Down
                270f -> SnakeDirection.Left
                else -> SnakeDirection.Up
            }
            onClick.invoke(direction)
        },
        modifier = modifier
            .rotate(rotateDegree)
            .size(32.dp)
            .border(1.dp, MaterialTheme.colors.onSurface, CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
