package com.example.pkemonapipokedex.data.response

import com.google.gson.annotations.SerializedName

data class InformationMoveResponse(
    @SerializedName("effect_entries") val effect: List<EffectText>?,
    @SerializedName("name") val name: String?,
    @SerializedName("power") val power: Int?,
    @SerializedName("pp") val pp: Int?,
)

data class EffectText(
    @SerializedName("effect") val effect: String?
)
