package com.shashank.messagebasedmodularization

import arrow.core.Either
import com.shashank.matchcenter.MatchCardModule
import com.shashank.matchcenter.MatchCenterModule
import com.shashank.modulecontracts.Message
import com.shashank.modulecontracts.Status
import com.shashank.router.IModule
import com.shashank.router.Router
import java.util.*

object AppModule : IModule {

    override val moduleId: String = UUID.randomUUID().toString()

    val modules = arrayListOf(this, MatchCenterModule, MatchCardModule)

    fun init() {
        Router.init(modules)
    }

    override fun register() {
        Router.subscribe("app", moduleId) {msg, id ->
            Either.right(Status.Success)
        }
    }
}