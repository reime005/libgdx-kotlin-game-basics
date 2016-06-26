package com.hmack.circlespawn.utils

/**
 * All resources listed in an enum class.
 *
 * Created by Marius Reimer on 12-Jun-16.
 */
object Resources {
    val SPRITES_ATLAS_PATH = "sprites.atlas"

    enum class RegionNames(val str: String) {
        BACKGROUND_NAME("background"),
        BUTTON_PLAY_NAME("button_play"),
        BUTTON_PAUSE_NAME("button_pause"),
        BUTTON_RESUME_NAME("button_resume"),
        BUTTON_AGAIN_NAME("button_again"),
        BUTTON_QUIT_NAME("button_quit");
    }
}