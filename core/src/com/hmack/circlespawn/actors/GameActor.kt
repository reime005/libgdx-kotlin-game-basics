package com.hmack.circlespawn.actors

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.scenes.scene2d.Actor
import com.hmack.circlespawn.enums.GameState
import com.hmack.circlespawn.userdata.UserData
import com.hmack.circlespawn.utils.GameSettings

/**
 * A basic class that you can derive from to create actors.
 *
 * Created by Marius Reimer on 11-Jun-16.
 */
open class GameActor : Actor {

    lateinit var body: Body
    lateinit var userData: UserData

    constructor(body: Body) {
        this.body = body
        userData = body.userData as UserData
    }

    override fun act(delta: Float) {
        super.act(delta)

        if (!GameSettings.gameState.equals(GameState.RUNNING)) {
            return
        }

        if (body.userData == null) {
            remove()
        }
    }
}