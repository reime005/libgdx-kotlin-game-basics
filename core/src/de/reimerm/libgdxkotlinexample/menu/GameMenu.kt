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

package de.reimerm.libgdxkotlinexample.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Disposable
import de.reimerm.libgdxkotlinexample.enums.GameState
import de.reimerm.libgdxkotlinexample.main.MainGame
import de.reimerm.libgdxkotlinexample.main.MainScreen
import de.reimerm.libgdxkotlinexample.utils.AssetsManager
import de.reimerm.libgdxkotlinexample.utils.GameSettings
import de.reimerm.libgdxkotlinexample.utils.Resources

/**
 * Implementation of the in-game UI, like the play or volume buttons, score, ...
 *
 * Created by Marius Reimer on 19-Jun-16.
 */
class GameMenu : Disposable {

    private lateinit var pauseResumeButton: ImageButton

    lateinit var table: Table
        private set

    constructor() {
        table = Table()
        table.setFillParent(true)
        table.pad(Gdx.graphics.height.toFloat() * 0.025f)

//        table.debug()

        val style = ImageButton.ImageButtonStyle()
        val draw: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_QUIT_NAME.name]).drawable
        style.imageUp = draw

        val stylePauseResume = ImageButton.ImageButtonStyle()
        val drawPauseResume: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_PAUSE_NAME.name]).drawable
        stylePauseResume.imageUp = drawPauseResume

        val menuButton = ImageButton(style)
        menuButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                MainGame.screen = MenuScreen()
                return false
            }
        })

        pauseResumeButton = ImageButton(stylePauseResume)
        pauseResumeButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                when (GameSettings.gameState) {
                    GameState.OVER -> {
                        MainGame.screen = MainScreen()
                    }

                    GameState.RUNNING -> {
                        GameSettings.gameState = GameState.PAUSED
                        onPause()
                    }

                    GameState.PAUSED -> {
                        GameSettings.gameState = GameState.RUNNING
                        onResume()
                    }
                }

                return false
            }
        })

        table.add(pauseResumeButton).top().left().size(Gdx.graphics.width * 0.08f, Gdx.graphics.width * 0.08f).expand()
        table.add(menuButton).top().right().size(Gdx.graphics.width * 0.08f, Gdx.graphics.width * 0.08f)
    }

    fun onPause() {
        pauseResumeButton.style.imageUp = Image(AssetsManager.textureMap.get(Resources.RegionNames.BUTTON_RESUME_NAME.name)).drawable
    }

    fun onResume() {
        pauseResumeButton.style.imageUp = Image(AssetsManager.textureMap.get(Resources.RegionNames.BUTTON_PAUSE_NAME.name)).drawable
    }

    override fun dispose() {
    }
}