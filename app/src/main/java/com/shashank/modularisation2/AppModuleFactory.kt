package com.shashank.modularisation2

import com.shashank.home.HomeModule
import com.shashank.homecontract.IHome
import com.shashank.login.LoginModule
import com.shashank.logincontract.ILoginModule

object AppModuleFactory {

    val navigationController = NavigationController();

    val loginModule : ILoginModule  by lazy { LoginModule }

    val homeModule : IHome  by lazy { HomeModule }

    init {
        loginModule.registerLoginListener(navigationController)
    }
}