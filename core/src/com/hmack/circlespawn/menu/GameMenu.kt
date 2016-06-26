package com.hmack.circlespawn.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Disposable
import com.hmack.circlespawn.enums.GameState
import com.hmack.circlespawn.main.MainGame
import com.hmack.circlespawn.main.MainScreen
import com.hmack.circlespawn.utils.AssetsManager
import com.hmack.circlespawn.utils.GameSettings
import com.hmack.circlespawn.utils.Resources

/**
 * Implementation of the in-game UI, like the play or volume buttons, score, ...
 *
 * Created by Marius Reimer on 19-Jun-16.
 */
class GameMenu : Disposable {

    private lateinit var pauseResumeButton: ImageButton

    lateinit var table: Table
        private set

    constructor() {
        table = Table()
        table.setFillParent(true)
        table.pad(Gdx.graphics.height.toFloat() * 0.025f)

//        table.debug()

        val style = ImageButton.ImageButtonStyle()
        val draw: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_QUIT_NAME.name]).drawable
        style.imageUp = draw

        val stylePauseResume = ImageButton.ImageButtonStyle()
        val drawPauseResume: Drawable = Image(AssetsManager.textureMap[Resources.RegionNames.BUTTON_PAUSE_NAME.name]).drawable
        stylePauseResume.imageUp = drawPauseResume

        val menuButton = ImageButton(style)
        menuButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                MainGame.screen = MenuScreen()
                return false
            }
        })

        pauseResumeButton = ImageButton(stylePauseResume)
        pauseResumeButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                when (GameSettings.gameState) {
                    GameState.OVER -> {
                        MainGame.screen = MainScreen()
                    }

                    GameState.RUNNING -> {
                        GameSettings.gameState = GameState.PAUSED
                        onPause()
                    }

                    GameState.PAUSED -> {
                        GameSettings.gameState = GameState.RUNNING
                        onResume()
                    }
                }

                return false
            }
        })

        table.add(pauseResumeButton).top().left().size(Gdx.graphics.width * 0.08f, Gdx.graphics.width * 0.08f).expand()
        table.add(menuButton).top().right().size(Gdx.graphics.width * 0.08f, Gdx.graphics.width * 0.08f)
    }

    fun onPause() {
        pauseResumeButton.style.imageUp = Image(AssetsManager.textureMap.get(Resources.RegionNames.BUTTON_RESUME_NAME.name)).drawable
    }

    fun onResume() {
        pauseResumeButton.style.imageUp = Image(AssetsManager.textureMap.get(Resources.RegionNames.BUTTON_PAUSE_NAME.name)).drawable
    }

    override fun dispose() {
    }
}