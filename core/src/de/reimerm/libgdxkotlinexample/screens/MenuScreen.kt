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

package de.reimerm.libgdxkotlinexample.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import de.reimerm.libgdxkotlinexample.abstract.AbstractStage
import de.reimerm.libgdxkotlinexample.abstract.AbstractStretchStage
import de.reimerm.libgdxkotlinexample.actors.BackgroundActor
import de.reimerm.libgdxkotlinexample.enums.GameState
import de.reimerm.libgdxkotlinexample.main.MainGame
import de.reimerm.libgdxkotlinexample.utils.AssetsManager
import de.reimerm.libgdxkotlinexample.utils.GameManager
import de.reimerm.libgdxkotlinexample.utils.GameSettings
import de.reimerm.libgdxkotlinexample.utils.Resources

/**
 * Screen for the Main Menu.
 *
 * Created by Marius Reimer on 18-Jun-16.
 */
class MenuScreen : Screen {

    private var stage: Stage
    private var stretchStage: Stage
    private var exitButton: Button

    constructor() {
        stage = MenuScreenStage()
        stretchStage = MenuScreenStretchStage()
        Gdx.input.inputProcessor = stage

        val table: Table = Table()
        stage.addActor(table)
        table.setFillParent(true)
        table.pad(GameSettings.HEIGHT * 0.025f)

        val styleExitButton = ImageButton.ImageButtonStyle()
        val drawExitButton: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_QUIT_NAME.name]).drawable
        styleExitButton.imageUp = drawExitButton

        val stylePlayButton = ImageButton.ImageButtonStyle()
        val drawPlayButton: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_PLAY_NAME.name]).drawable
        stylePlayButton.imageUp = drawPlayButton

//        table.debug()

        exitButton = ImageButton(styleExitButton)
        exitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                AssetsManager.dispose()
                System.exit(0)
            }
        })

        val playButton: ImageButton = ImageButton(stylePlayButton)
        playButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                MainGame.screen = MainScreen()
                GameManager.listener?.login()
                return false
            }
        })

        table.add(playButton).expand().center().bottom().right().size(GameSettings.WIDTH * 0.15f, GameSettings.WIDTH * 0.15f)
        table.add(exitButton).expand().top().right().size(GameSettings.WIDTH * 0.08f, GameSettings.WIDTH * 0.08f)
        GameManager.listener?.logout()
    }

    override fun show() {
        GameManager.gameState = GameState.RUNNING
    }

    override fun pause() {
        GameManager.gameState = GameState.PAUSED
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
        stretchStage.viewport.update(width, height, true)
    }

    override fun hide() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        // Start the render process
        stretchStage.viewport.apply()
        stretchStage.draw()

        stage.viewport.apply()
        stage.draw()
        stage.act(delta)
    }

    override fun resume() {
    }

    override fun dispose() {
        stage.dispose()
        stretchStage.dispose()
    }

    private inner class MenuScreenStage : AbstractStage() {

        override fun keyDown(keyCode: Int): Boolean {
            when (keyCode) {
                Input.Keys.BACK -> {
                    exitButton.toggle()
                }
            }
            return super.keyDown(keyCode)
        }
    }

    private inner class MenuScreenStretchStage : AbstractStretchStage {

        constructor() : super() {
            addActor(BackgroundActor(AssetsManager.textureMap[Resources.RegionNames.BACKGROUND_NAME.name]))
        }
    }
}