package de.reimerm.libgdxkotlinexample.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import de.reimerm.libgdxkotlinexample.utils.GameSettings

/**
 * Created by Marius Reimer on 08-Jul-16.
 */
class BackgroundActor(val textureRegion: TextureRegion?) : Actor() {

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.color = color
        batch.color.a *= parentAlpha
        batch.draw(textureRegion, 0f, 0f, GameSettings.WIDTH, GameSettings.HEIGHT)
        batch.color = Color.BLACK
        super.draw(batch, parentAlpha)
    }
}