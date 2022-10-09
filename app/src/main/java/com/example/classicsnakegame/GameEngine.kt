package com.example.classicsnakegame

import android.util.Log
import com.example.classicsnakegame.model.GameState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class GameEngine(
    scope: CoroutineScope,
    onFoodEaten: () -> Unit,
    onGameOver: () -> Unit
) {
    private var isPlaying = true
    private val food: Int
        get() = Random().nextInt(NUMBER_OF_PIXELS)
    private val snakeStartPoint = 45
    private val initSnake = listOf(snakeStartPoint, snakeStartPoint + NUMBER_IN_ROW, snakeStartPoint + NUMBER_IN_ROW * 2)
    private val initState = GameState(food, initSnake, SnakeDirection.Down)

    private val _gameState = MutableStateFlow(initState)
    val gameState: Flow<GameState> = _gameState

    var move = NUMBER_IN_ROW

    init {
        scope.launch {
            var snackLength = initSnake.size
            while (isPlaying) {
                delay(200)
                _gameState.update { state ->
//                    val hasReachedHorizontalEnd = state.snack.first() % NUMBER_IN_ROW == 0 && (state.direction == SnackDirection.Left || state.direction == SnackDirection.Right)
//                    val hasReachedVerticalEnd = state.snack.first() % NUMBER_OF_PIXELS

                    val newPos = state.snake.first().let {
                        (it + move + NUMBER_OF_PIXELS) % NUMBER_OF_PIXELS
//                        it + move
                    }
                    Log.e("math_log", newPos.toString())

                    if (newPos == state.food) {
                        snackLength++
                        onFoodEaten.invoke()
                    }

                    if (state.snake.contains(newPos)) {
                        snackLength = initSnake.size
                        _gameState.value = initState
                        onGameOver.invoke()
                    }

                    state.copy(
                        food = if (newPos == state.food) food else state.food,
                        snake = listOf(newPos) + state.snake.take(snackLength - 1)
                    )
                }
            }
        }
    }

}