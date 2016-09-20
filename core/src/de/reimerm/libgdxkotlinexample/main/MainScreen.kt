/*
 * Copyright (c) 2016. Marius Reimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.reimerm.libgdxkotlinexample.main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import de.reimerm.libgdxkotlinexample.enums.GameState
import de.reimerm.libgdxkotlinexample.utils.AssetsManager
import de.reimerm.libgdxkotlinexample.utils.GameSettings

/**
 * Screen for the main game.
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
class MainScreen : Screen {

    private lateinit var stage: de.reimerm.libgdxkotlinexample.main.MainStage

    constructor() {
        if (!AssetsManager.fullyLoaded) {
            AssetsManager.loadAssets()
            AssetsManager.manager.finishLoading()
        }
        stage = de.reimerm.libgdxkotlinexample.main.MainStage()
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