package com.shashank.matchcenter

import arrow.core.Either
import com.shashank.modulecontracts.Message
import com.shashank.modulecontracts.Status
import com.shashank.router.IModule
import com.shashank.router.Router
import java.util.*

object MatchCenterModule : IModule {


    override val moduleId: String = UUID.randomUUID().toString()

    val supportedMsg = arrayOf("open_match_center")

    init {
        register()
    }

    override fun register() {
        Router.subscribe("open_match_center", moduleId) { msg, id ->
            Router.publish("return_open_match_center", id, moduleId,
                    Message.FragmentMessage(MatchCenterFragment.newInstance()))
            Either.right(Status.Success)
        }
    }
}