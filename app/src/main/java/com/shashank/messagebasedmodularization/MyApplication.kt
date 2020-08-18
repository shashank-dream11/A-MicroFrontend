package com.shashank.messagebasedmodularization

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppModule.init()
    }
}