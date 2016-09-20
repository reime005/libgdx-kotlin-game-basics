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

package de.reimerm.libgdxkotlinexample.listeners

import com.badlogic.gdx.physics.box2d.*

/**
 * With this ContactListener you can react on contact between bodies.
 *
 * Created by Marius Reimer on 11-Jun-16.
 */
class Contacts : ContactListener {

    override fun endContact(contact: Contact?) {
    }

    override fun beginContact(contact: Contact) {
        val bodyA: Body = contact.fixtureA.body
        val bodyB: Body = contact.fixtureB.body

        // do something with the bodies...
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
    }
}