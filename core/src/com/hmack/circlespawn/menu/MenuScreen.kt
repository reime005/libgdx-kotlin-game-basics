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

package com.hmack.circlespawn.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.hmack.circlespawn.enums.GameState
import com.hmack.circlespawn.main.MainGame
import com.hmack.circlespawn.main.MainScreen
import com.hmack.circlespawn.utils.AssetsManager
import com.hmack.circlespawn.utils.GameSettings
import com.hmack.circlespawn.utils.Resources

/**
 * Screen for the Main Menu.
 *
 * Created by Marius Reimer on 18-Jun-16.
 */
class MenuScreen : Screen {

    private lateinit var stage: MenuStage

    constructor() {
        stage = MenuStage()
        Gdx.input.inputProcessor = stage
        stage.init(AssetsManager.textureMap[Resources.RegionNames.BACKGROUND_NAME.name])

        val table: Table = Table()
        stage.addActor(table)
        table.setFillParent(true)
        table.pad(Gdx.graphics.height.toFloat() * 0.025f)

        val styleExitButton = ImageButton.ImageButtonStyle()
        val drawExitButton: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_QUIT_NAME.name]).drawable
        styleExitButton.imageUp = drawExitButton

        val stylePlayButton = ImageButton.ImageButtonStyle()
        val drawPlayButton: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_PLAY_NAME.name]).drawable
        stylePlayButton.imageUp = drawPlayButton

//        table.debug()

        val exitButton: ImageButton = ImageButton(styleExitButton)
        exitButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                AssetsManager.dispose()
                System.exit(0)
                return false
            }
        })

        val playButton: ImageButton = ImageButton(stylePlayButton)
        playButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                MainGame.screen = MainScreen()
                return false
            }
        })

        table.add(playButton).expand().center().bottom().right().size(Gdx.graphics.width * 0.15f, Gdx.graphics.width * 0.15f)
        table.add(exitButton).expand().top().right().size(Gdx.graphics.width * 0.08f, Gdx.graphics.width * 0.08f)
    }

    override fun show() {
        GameSettings.gameState = GameState.RUNNING;
    }

    override fun pause() {
        GameSettings.gameState = GameState.PAUSED
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true);
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

    private inner class MenuStage : Stage() {

        val actor: BackgroundActor = BackgroundActor()

        fun init(texture: TextureRegion?) {
            actor.backgroundTexture = texture
            addActor(actor)
        }

        private inner class BackgroundActor : Actor() {
            var backgroundTexture: TextureRegion? = null

            override fun draw(batch: Batch?, parentAlpha: Float) {
                super.draw(batch, parentAlpha)

                if (backgroundTexture != null) {
                    batch?.draw(backgroundTexture, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
                }
            }
        }
    }
}