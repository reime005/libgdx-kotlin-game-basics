package de.reimerm.libgdxkotlinexample

import de.reimerm.libgdxkotlinexample.utils.GameEventListener

/**
 * Created by Marius Reimer on 20-Sep-16.
 */
class AndroidGameEventListener : GameEventListener {
    override fun login() {
        LoginHandler.getInstance().login()
    }

    override fun logout() {
        LoginHandler.getInstance().logout()
    }

    override fun isLoggedIn(): Boolean {
        return LoginHandler.getInstance().isConnected
    }
}