package com.vktest.vktest.di

import com.vktest.data.network.models.ValutesDto
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
    fun provideRetrofit() : ValutesDto {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ValutesDto::class.java)
    }

}