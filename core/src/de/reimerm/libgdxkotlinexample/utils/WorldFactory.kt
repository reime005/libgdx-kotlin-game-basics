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

package de.reimerm.libgdxkotlinexample.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import de.reimerm.libgdxkotlinexample.enums.UserDataType
import de.reimerm.libgdxkotlinexample.userdata.UserData

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
        def.position.set(Gdx.app.graphics.width / 2f, GameSettings.HEIGHT / 2f);

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