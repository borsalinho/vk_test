package com.vktest.data.network.api

import com.vktest.data.network.models.ValutesDto
import retrofit2.http.GET

interface ApiCbrDaily {

    @GET("daily_json.js")
    suspend fun getApiResponce() : ValutesDto
}