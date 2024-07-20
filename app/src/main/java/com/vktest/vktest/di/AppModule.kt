package com.vktest.vktest.di

import android.content.Context
import com.vktest.vktest.presentation.ui.mainactivity.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context : Context) {
    @Singleton
    @Provides
    fun providerContext() : Context{
        return context
    }

    @Singleton
    @Provides
    fun provideMainViewModel() : MainViewModel {
        return MainViewModel()
    }
}