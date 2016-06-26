package com.hmack.circlespawn.listeners

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