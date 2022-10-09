package com.example.classicsnakegame.model

data class GameState(
    val food: Int,
    val snake: List<Int>,
    val direction: Int
)
