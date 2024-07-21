package com.vktest.domain.usecases

import com.vktest.domain.models.Valutes
import com.vktest.domain.repository.ValuteRepository

class GetValutesUseCase(val valuteRepository: ValuteRepository) {

    suspend fun execute() : Valutes{
        return valuteRepository.getValutes()
    }
}