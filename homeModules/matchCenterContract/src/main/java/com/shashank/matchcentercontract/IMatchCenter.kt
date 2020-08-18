package com.shashank.matchcentercontract

import androidx.fragment.app.Fragment
import com.shashank.prelude.IModuleContract

interface IMatchCenter : IModuleContract {

    fun getMatchCenterFragment() : Fragment

    fun setMatchCenterCallback(callback: IMatchCenterCallback)

}

interface  IMatchCenterCallback {
    fun onMatchClicked()
}