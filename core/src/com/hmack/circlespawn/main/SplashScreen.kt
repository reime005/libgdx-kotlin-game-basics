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