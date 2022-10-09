package com.example.classicsnakegame

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.classicsnakegame.components.*
import java.util.*

val SQUARE_CORNER_RADIUS_SIZE = 2.dp

const val NUMBER_IN_ROW = 32
const val NUMBER_OF_PIXELS = NUMBER_IN_ROW * NUMBER_IN_ROW

lateinit var game: GameEngine

private val score = mutableStateOf(0)

@Composable
fun Screen(scope: LifecycleCoroutineScope) {
    game = GameEngine(scope = scope, onFoodEaten = { score.value++ }, onGameOver = { score.value = 0 })
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        TitleScreen()
        SampleBoard()
        Controller() {
            when (it) {
                SnakeDirection.Down -> game.move = NUMBER_IN_ROW
                SnakeDirection.Up -> game.move = -NUMBER_IN_ROW
                SnakeDirection.Left -> game.move = -1
                SnakeDirection.Right -> game.move = 1
            }
        }
    }
}

@Composable
private fun TitleScreen() {
    Text(text = "Your Score: ${score.value}")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SampleBoard() {
    val gameState = game.gameState.collectAsState(initial = null)
    gameState.value?.let {
        LazyVerticalGrid(
            cells = GridCells.Fixed(NUMBER_IN_ROW),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .border(1.dp, MaterialTheme.colors.onSurface, RoundedCornerShape(8.dp))
        ) {
            items(NUMBER_OF_PIXELS) { index ->
                val type = if (it.snake.contains(index)) {
                    SNAKE_PIXEL_TYPE
                } else if (index == it.food) {
                    FOOD_PIXEL_TYPE
                } else {
                    BOARD_PIXEL_TYPE
                }
                Pixel(type)
            }
        }
    }
}

