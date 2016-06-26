package com.hmack.circlespawn.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.hmack.circlespawn.enums.UserDataType
import com.hmack.circlespawn.userdata.UserData

/**
 * Created by Marius Reimer on 10-Jun-16.
 */
object WorldFactory {
    fun createWorld(): World {
        return World(GameSettings.WORLD_GRAVITY, true)
    }

    /**
     * Example of a method that creates a circle body.
     *
     */
    fun createCircle(world: World, position: Vector2, radius: Float, type: UserDataType): Body {
        val def: BodyDef = BodyDef()
        def.type = BodyDef.BodyType.DynamicBody
        def.position.set(Gdx.app.graphics.width / 2f, Gdx.graphics.height / 2f);

        val body: Body = world.createBody(def)

        val shape: CircleShape = CircleShape()
        shape.radius = radius

        val fixtureDef: FixtureDef = FixtureDef()
        fixtureDef.shape = shape
        fixtureDef.density = 0f
        fixtureDef.friction = 0f
        fixtureDef.restitution = 0f

        body.userData = UserData(type, radius, radius)
        body.createFixture(fixtureDef)

        shape.dispose()
        return body
    }
}