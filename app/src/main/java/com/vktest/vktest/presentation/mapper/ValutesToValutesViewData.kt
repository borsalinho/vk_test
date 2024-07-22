package com.vktest.vktest.presentation.mapper


import com.vktest.domain.models.Valutes
import com.vktest.vktest.presentation.models.ValuteViewData
import com.vktest.vktest.presentation.models.ValutesViewData

fun Valutes.toValutesViewData() : ValutesViewData {
    val valutesViewData = mutableMapOf<String, ValuteViewData>()
    valutes.forEach { (key, value) ->
        valutesViewData[key] = ValuteViewData(value.value, value.name)
    }
    return ValutesViewData(valutesViewData)
}