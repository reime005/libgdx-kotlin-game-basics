package com.hmack.circlespawn.main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.hmack.circlespawn.menu.MenuScreen
import com.hmack.circlespawn.utils.GameSettings

/**
 * Screen that is shown once when the app is starting and loading assets.
 *
 * Created by Marius Reimer on 18-Jun-16.
 */
class SplashScreen : Screen {

    private lateinit var stage: Stage
    private var time = 0f

    constructor() {
        stage = Stage()
    }

    override fun show() {
    }

    override fun pause() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Start the render process
        stage.draw()
        stage.act(delta)

        time += delta
        if (time > GameSettings.FIRST_SPLASH_SCREEN_TIME) {
            MainGame.screen = MenuScreen()
        }
    }

    override fun resume() {
    }

    override fun dispose() {
    }
}