package com.shashank.home

import android.content.Context
import android.content.Intent
import com.shashank.homecontract.IHome

object HomeModule : IHome {
    override fun startHomeActivity(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }
}