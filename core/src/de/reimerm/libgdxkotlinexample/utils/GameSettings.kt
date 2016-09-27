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

import com.badlogic.gdx.math.Vector2

/**
 * Singleton to manage game relevant settings.
 *
 * Created by Marius Reimer on 10-Jun-16.
 */
object GameSettings {
    val PREFERENCES_GENERAL = "general_prefs"
    val MUSIC_PREFERENCES = "music"
    const val TIME_STEP: Float = 1 / 250f;
    const val HEIGHT = 480f
    const val WIDTH = 800f
    val WORLD_GRAVITY: Vector2 = Vector2(0f, 0f)
    const val FIRST_SPLASH_SCREEN_TIME = 2f
    const val SECOND_SPLASH_SCREEN_TIME = 2f
}