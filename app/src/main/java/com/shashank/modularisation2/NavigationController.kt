package com.shashank.modularisation2

import com.shashank.logincontract.ILoginCallback


class NavigationController : ILoginCallback {
    override fun onLoginSuccess(userName: String) {
        //Check with DeepLink Feature
        //May be Navigation Module
        //Route to Next Feature
        AppModuleFactory.homeModule.startHomeActivity(MyApplication.getInstance())
    }
}