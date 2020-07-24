package com.shashank.home

import com.shashank.matchcenter.MatchCenterModule
import com.shashank.matchcentercontract.IMatchCenter
import com.shashank.matchcentercontract.IMatchCenterCallback

object ModuleFactory {

    val matchCenter : IMatchCenter by lazy { MatchCenterModule }

    init {
        matchCenter.setMatchCenterCallback(NavigationController)
    }
}

object NavigationController : IMatchCenterCallback {
    override fun onMatchClicked(): Int {
        //redirect
    }
}