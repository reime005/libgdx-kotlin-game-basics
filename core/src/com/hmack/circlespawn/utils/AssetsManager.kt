package com.hmack.circlespawn.utils

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
        get() = manager.isLoaded(Resources.SPRITES_ATLAS_PATH)
        private set

    fun loadAssets() {
        manager = AssetManager()
        manager.load(Resources.SPRITES_ATLAS_PATH, TextureAtlas::class.java)
    }

    fun loadAtlas() {
        textureAtlas = manager.get(Resources.SPRITES_ATLAS_PATH, TextureAtlas::class.java)

        for (e in Resources.RegionNames.values()) {
            textureMap.put(e.name, textureAtlas.findRegion(e.str))
        }
    }

    override fun dispose() {
        textureAtlas.dispose()
        textureMap.clear()
        manager.dispose()
    }
}