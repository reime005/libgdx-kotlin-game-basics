package de.reimerm.libgdxkotlinexample.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences

/**
 * Created by Marius Reimer on 04-Jul-16.
 */
object PersistenceManager {

    private val preferences: Preferences = Gdx.app.getPreferences(GameSettings.PREFERENCES_GENERAL)

    @Synchronized
    fun saveBoolean(key: String, value: Boolean) {
        preferences.putBoolean(key, value)
        preferences.flush()
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return preferences.getBoolean(key, default)
    }

    fun reset() {
        preferences.clear()
    }
}