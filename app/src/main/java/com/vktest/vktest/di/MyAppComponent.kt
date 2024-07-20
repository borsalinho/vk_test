package com.vktest.vktest.di

import com.vktest.vktest.presentation.ui.mainactivity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface MyAppComponent {
    fun inject(mainActivity: MainActivity)
}