package com.vktest.data.network.models

import com.google.gson.annotations.SerializedName

data class ValuteDto(
    @SerializedName("Value")
    val value : Double,

    @SerializedName("Name")
    val name : String
)

