package de.reimerm.libgdxkotlinexample.utils

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Disposable

/**
 * Created by Marius Reimer on 08-Jul-16.
 */
object AudioUtils : Disposable {

    private var music: Music? = null

    fun init() {
        music = AssetsManager.manager.get(Resources.BACKGROUND_MUSIC, Music::class.java)
        music?.isLooping = true
        music?.volume = 0.1f
        playBackgroundMusic()
    }

    fun playBackgroundMusic() {
        if (/*isMusicPreferenceTrue() && */music?.isPlaying == false) {
            music?.play()
        }
    }

    fun playSound(sound: Sound) {
        if (isMusicOn() == true) {
            sound.play()
        }
    }

    fun stopMusic() {
        music?.pause()
    }

    fun isMusicOn(): Boolean? {
        return music?.isPlaying
    }

    fun toggleMusicPreference() {
        PersistenceManager.saveBoolean(GameSettings.MUSIC_PREFERENCES, !isMusicPreferenceTrue())
    }

    fun isMusicPreferenceTrue(): Boolean {
        return PersistenceManager.getBoolean(GameSettings.MUSIC_PREFERENCES, true)
    }

    override fun dispose() {
        music?.dispose()
    }
}