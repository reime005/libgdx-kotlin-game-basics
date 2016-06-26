package com.hmack.circlespawn.main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.hmack.circlespawn.enums.GameState
import com.hmack.circlespawn.utils.AssetsManager
import com.hmack.circlespawn.utils.GameSettings

/**
 * Screen for the main game.
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
class MainScreen : Screen {

    private lateinit var stage: MainStage

    constructor() {
        if (!AssetsManager.fullyLoaded) {
            AssetsManager.loadAssets()
            AssetsManager.manager.finishLoading()
        }
        stage = MainStage()
    }

    override fun show() {
    }

    override fun pause() {
        GameSettings.gameState = GameState.PAUSED
        stage.menu.onPause()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun hide() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Start the render process
        stage.draw()
        stage.act(delta)
    }

    override fun resume() {
    }

    override fun dispose() {
        stage.dispose()
    }
}