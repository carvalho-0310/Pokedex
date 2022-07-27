package com.example.pkemonapipokedex.data.response

import com.google.gson.annotations.SerializedName

data class InformationPokemonResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("types") val types: List<Types>,
)

data class Sprites(
    @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default") val imageUrl: String
)

data class Types(
    @SerializedName("type") val typeName: TypeName
)

data class TypeName(
    @SerializedName("name") val nameType: String
)
