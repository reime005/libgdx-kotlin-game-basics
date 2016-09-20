package de.reimerm.libgdxkotlinexample.utils

/**
 * Created by Marius Reimer on 20-Sep-16.
 */
interface GameEventListener {
    fun login()
    fun logout()
    fun isLoggedIn(): Boolean
}