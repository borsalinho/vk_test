package com.vktest.data.network.models

import com.google.gson.annotations.SerializedName

data class ValutesDto(
    @SerializedName("Valute")
    val valutes: Map<String,ValuteDto>
)
