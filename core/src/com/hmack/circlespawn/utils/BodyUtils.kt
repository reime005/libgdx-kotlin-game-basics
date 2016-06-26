package com.hmack.circlespawn.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.physics.box2d.Body

/**
 * Defines operations on bodies.
 *
 * Created by Marius Reimer on 22-Jun-16.
 */
object BodyUtils {

    fun bodyIsOutOfWorld(body: Body): Boolean {
        return ((body.position.x < 0 || body.position.y < 0 || body.position.x > Gdx.graphics.width || body.position.y > Gdx.graphics.height))
    }
}