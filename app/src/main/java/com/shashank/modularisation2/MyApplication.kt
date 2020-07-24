package com.shashank.modularisation2

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private lateinit var instance : MyApplication

        fun getInstance() = instance
    }
}