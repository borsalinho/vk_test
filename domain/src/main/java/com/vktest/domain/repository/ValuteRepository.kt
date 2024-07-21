package com.vktest.domain.repository

import com.vktest.domain.models.Valutes

interface ValuteRepository {
    suspend fun getValutes() : Valutes
}