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
import com.badlogic.gdx.Input
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Array
import de.reimerm.libgdxkotlinexample.abstract.AbstractStage
import de.reimerm.libgdxkotlinexample.enums.GameState
import de.reimerm.libgdxkotlinexample.listeners.Contacts
import de.reimerm.libgdxkotlinexample.menu.GameMenu
import de.reimerm.libgdxkotlinexample.screens.MenuScreen
import de.reimerm.libgdxkotlinexample.utils.BodyUtils
import de.reimerm.libgdxkotlinexample.utils.GameManager
import de.reimerm.libgdxkotlinexample.utils.GameSettings
import de.reimerm.libgdxkotlinexample.utils.WorldFactory

/**
 * The main logic for the game. The Stage may has many actors and a world, handles events, ...
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
class MainStage : AbstractStage {

    private lateinit var world: World

    private var renderer: Box2DDebugRenderer = Box2DDebugRenderer()

    private var accumulator: Float = 0f
    private var time: Float = 0f

    constructor() : super() {
        clear()
        setupWorld()
        GameManager.reset()
        setupMenu()
        createTouchControlAreas()
    }

    /**
     * On Game Restart (Reset all values)
     */
    fun onStart() {

    }

    /**
     * Un-Comment this method to add the debug render
     */
    //    override fun draw() {
//        super.draw()
//        renderer.render(world, camera.combined)
//    }

    private fun setupWorld() {
        GameManager.world = WorldFactory.createWorld()
        GameManager.world.setContactListener(Contacts())
    }

    private fun setupMenu() {
        val menu = GameMenu()
        addActor(menu.table)
        GameManager.menu = menu
    }

    private fun createTouchControlAreas() {
        Gdx.input.inputProcessor = this
    }

    private fun update(body: Body) {
        // remove if body is out of world
        if (BodyUtils.bodyIsOutOfWorld(body)) {
            GameManager.addBodyToRemove(body)
        }
    }

    override fun act(delta: Float) {
        super.act(delta)

        if (GameManager.gameState != GameState.RUNNING) {
            return
        }

        if (time <= GameSettings.SECOND_SPLASH_SCREEN_TIME) {
            time += delta
            return
        }

        for (b in GameManager.bodiesToRemove) {
            world.destroyBody(b)
            GameManager.destroyBody(b)
        }

        val bodies: Array<Body> = Array(GameManager.world.bodyCount)
        world.getBodies(bodies)

        for (b in bodies) {
            update(b)
        }

        // increment a step in our game
        accumulator += delta
        while (accumulator >= delta) {
            GameManager.world.step(GameSettings.TIME_STEP, 6, 2)
            accumulator -= GameSettings.TIME_STEP
        }
    }

    override fun keyDown(keyCode: Int): Boolean {
        when (keyCode) {
            Input.Keys.BACK -> MainGame.screen = MenuScreen()
        }
        return super.keyDown(keyCode)
    }

    override fun dispose() {
        super.dispose()
        renderer.dispose()
        world.dispose()
    }
}