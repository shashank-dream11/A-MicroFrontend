package com.shashank.homecontract

import android.content.Context
import com.shashank.prelude.IModuleContract

interface IHome : IModuleContract {

    fun startHomeActivity(context: Context)
}