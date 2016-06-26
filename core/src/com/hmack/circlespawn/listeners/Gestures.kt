package com.hmack.circlespawn.listeners

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World

/**
 * With this listener you can receive gestures.
 *
 * You can also derive from GestureDetector.GestureAdapter, if you only want to implement a subset
 * of GestureDetector.GestureListener.
 *
 * Created by Marius Reimer on 21-Jun-16.
 */
object Gestures : GestureDetector.GestureListener {

    lateinit var world: World
    lateinit var camera: Camera

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return false
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return false
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        return false
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return false
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        return false
    }

    override fun pinchStop() {
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return false
    }
}