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