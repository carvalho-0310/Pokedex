package com.example.pkemonapipokedex.data.response

import com.google.gson.annotations.SerializedName

data class PagesPokemonResponse(
    @SerializedName("count") val count: Int,

    @SerializedName("results") val results: List<PokemonResponse>,
)

data class PokemonResponse(
    @SerializedName("name") val name :String,
    @SerializedName("url") val url :String,
)
