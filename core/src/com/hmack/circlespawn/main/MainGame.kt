package com.hmack.circlespawn.main

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.hmack.circlespawn.utils.AssetsManager

/**
 * Launcher for the game.
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
object MainGame : Game() {

    override fun create() {
        AssetsManager.loadAssets()
        AssetsManager.manager.finishLoading()
        AssetsManager.loadAtlas()
        setScreen(SplashScreen())
    }

    override fun setScreen(screen: Screen?) {
        super.setScreen(screen)
    }

    override fun dispose() {
        super.dispose()
        getScreen()?.dispose()
    }
}