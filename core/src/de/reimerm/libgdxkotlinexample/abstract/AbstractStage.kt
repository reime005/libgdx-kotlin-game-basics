package de.reimerm.libgdxkotlinexample.abstract

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ScalingViewport
import de.reimerm.libgdxkotlinexample.utils.GameSettings

/**
 * Created by Marius Reimer on 21-Jul-16.
 */
open class AbstractStage : Stage {

    constructor() : super(ScalingViewport(Scaling.stretch, GameSettings.WIDTH, GameSettings.HEIGHT, OrthographicCamera(GameSettings.WIDTH, GameSettings.HEIGHT))) {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
        camera.update()
    }
}