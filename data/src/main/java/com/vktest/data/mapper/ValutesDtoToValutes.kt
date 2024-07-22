package com.vktest.data.mapper

import com.vktest.data.network.models.ValutesDto
import com.vktest.domain.models.Valute
import com.vktest.domain.models.Valutes

fun ValutesDto.ToValutes() : Valutes {
    val valutes = mutableMapOf<String, Valute>()
    for ((key, value) in this.valutes) {
        valutes[key] = Valute(value.value, value.name)
    }
    return Valutes(valutes)
}