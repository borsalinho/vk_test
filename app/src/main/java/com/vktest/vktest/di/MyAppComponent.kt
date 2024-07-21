package com.vktest.vktest.di

import com.vktest.vktest.presentation.ui.fragments.CheckFragment
import com.vktest.vktest.presentation.ui.fragments.MainFragment
import com.vktest.vktest.presentation.ui.mainactivity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        CheckNetworkModule::class
    ]
)
interface MyAppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(checkFragment : CheckFragment)
    fun inject(mainFragment : MainFragment)
}