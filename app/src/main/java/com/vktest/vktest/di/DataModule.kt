package com.vktest.vktest.di

import com.vktest.data.implementation.ValuteRepositoryImpl
import com.vktest.data.network.api.ApiCbrDaily
import com.vktest.data.network.models.ValutesDto
import com.vktest.domain.repository.ValuteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    private val baseUrl = "https://www.cbr-xml-daily.ru/"

    @Singleton
    @Provides
    fun provideRetrofit() : ApiCbrDaily {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCbrDaily::class.java)
    }


    @Singleton
    @Provides
    fun provideValuteRepositoryImpl(
        apiCbrDaily : ApiCbrDaily
    ) : ValuteRepository {
        return ValuteRepositoryImpl(
            apiCbrDaily = apiCbrDaily
        )
    }
}