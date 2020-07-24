package com.shashank.logincontract

import androidx.fragment.app.Fragment
import com.shashank.prelude.IModuleContract

interface ILoginModule : IModuleContract {

    fun getLoginFragment() : Fragment

    fun registerLoginListener(callback : ILoginCallback)
}

interface ILoginCallback {

    fun onLoginSuccess(userName: String)
}