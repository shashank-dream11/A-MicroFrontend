package com.shashank.matchcenter

import com.shashank.matchcentercontract.IMatchCenter
import com.shashank.matchcentercontract.IMatchCenterCallback
import java.lang.ref.WeakReference

object MatchCenterModule : IMatchCenter {

    val callbacks = HashSet<WeakReference<IMatchCenterCallback>>()

    override fun getMatchCenterFragment() = MatchCenterFragment.newInstance()

    override fun setMatchCenterCallback(callback: IMatchCenterCallback) {
        if (callbacks.mapNotNull { it.get() }.contains(callback)) {
            callbacks.add(WeakReference(callback))
        }
    }
}