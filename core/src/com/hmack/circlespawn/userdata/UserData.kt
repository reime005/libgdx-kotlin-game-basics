package com.hmack.circlespawn.userdata

import com.hmack.circlespawn.enums.UserDataType

/**
 * Added to a body - holds information about it. Is an interface between the body and actor.
 *
 * Created by Marius Reimer on 11-Jun-16.
 */
open class UserData {

    lateinit var userDataType: UserDataType
    var width: Float = 0f
    var height: Float = 0f

    constructor(userDataType: UserDataType, width: Float, height: Float) {
        this.userDataType = userDataType
        this.width = width
        this.height = height
    }
}