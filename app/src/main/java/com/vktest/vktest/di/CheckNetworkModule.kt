package com.vktest.vktest.di

import android.content.Context
import com.vktest.checknet.CheckNetwork
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CheckNetworkModule() {

    @Singleton
    @Provides
    fun provideCheckNetwork(context : Context) : CheckNetwork {
        return CheckNetwork(context = context)
    }
}