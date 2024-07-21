package com.vktest.vktest.di

import com.vktest.domain.repository.ValuteRepository
import com.vktest.domain.usecases.GetValutesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetValutesUseCase(
        valuteRepository : ValuteRepository
    ) : GetValutesUseCase {
        return GetValutesUseCase(
            valuteRepository = valuteRepository
        )
    }
}