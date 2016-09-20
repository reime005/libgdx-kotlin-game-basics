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

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import java.util.*

/**
 * Stores all assets for the game.
 *
 * Created by Marius Reimer on 12-Jun-16.
 */
object AssetsManager : Disposable {

    var manager: AssetManager = AssetManager()
        private set

    var textureAtlas: TextureAtlas = TextureAtlas()
        private set

    var textureMap: HashMap<String, TextureRegion> = HashMap()
        private set

    var fullyLoaded: Boolean = false
        get() = manager.isLoaded(de.reimerm.libgdxkotlinexample.utils.Resources.SPRITES_ATLAS_PATH)
        private set

    fun loadAssets() {
        manager = AssetManager()
        manager.load(de.reimerm.libgdxkotlinexample.utils.Resources.SPRITES_ATLAS_PATH, TextureAtlas::class.java)
    }

    fun loadAtlas() {
        textureAtlas = manager.get(de.reimerm.libgdxkotlinexample.utils.Resources.SPRITES_ATLAS_PATH, TextureAtlas::class.java)

        for (e in de.reimerm.libgdxkotlinexample.utils.Resources.RegionNames.values()) {
            textureMap.put(e.name, textureAtlas.findRegion(e.str))
        }
    }

    override fun dispose() {
        textureAtlas.dispose()
        textureMap.clear()
        manager.dispose()
    }
}