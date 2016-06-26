package com.hmack.circlespawn.utils

import com.badlogic.gdx.math.Vector2
import com.hmack.circlespawn.enums.GameState

/**
 * Singleton to manage game relevant settings.
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
object GameSettings {
    var gameState: GameState = GameState.PAUSED
    const val TIME_STEP: Float = 1 / 250f;
    const val HEIGHT = 480f
    const val WIDTH = 800f
    val WORLD_GRAVITY: Vector2 = Vector2(0f, 0f)
    const val FIRST_SPLASH_SCREEN_TIME = 2f
    const val SECOND_SPLASH_SCREEN_TIME = 2f
}