package com.vktest.data.implementation

import com.vktest.data.mapper.ToValutes
import com.vktest.data.network.api.ApiCbrDaily
import com.vktest.domain.models.Valutes
import com.vktest.domain.repository.ValuteRepository

class ValuteRepositoryImpl(
    val apiCbrDaily : ApiCbrDaily
) : ValuteRepository {
    override suspend fun getValutes(): Valutes {
       return apiCbrDaily.getApiResponce().ToValutes()
    }
}