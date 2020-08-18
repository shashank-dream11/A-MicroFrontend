package com.shashank.router

import java.util.*

interface IModule {

    val moduleId: String

    fun register()
}