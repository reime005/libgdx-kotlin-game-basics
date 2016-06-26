package com.hmack.circlespawn.utils

import com.badlogic.gdx.physics.box2d.Body
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Singleton to manage game relevant objects.
 *
 * Created by Marius Reimer on 21-Jun-16.
 */
object GameManager {
    var bodiesToRemove: Set<Body> = Collections.newSetFromMap(ConcurrentHashMap<Body, Boolean>())
}