package com.vktest.vktest.app

import android.app.Application
import com.vktest.vktest.di.AppModule
import com.vktest.vktest.di.CheckNetworkModule
import com.vktest.vktest.di.DaggerMyAppComponent
import com.vktest.vktest.di.DataModule
import com.vktest.vktest.di.DomainModule
import com.vktest.vktest.di.MyAppComponent

class MyApp : Application() {
    lateinit var myAppComponent : MyAppComponent
    override fun onCreate() {
        super.onCreate()
        myAppComponent = DaggerMyAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .checkNetworkModule(CheckNetworkModule())
            .build()
    }
}