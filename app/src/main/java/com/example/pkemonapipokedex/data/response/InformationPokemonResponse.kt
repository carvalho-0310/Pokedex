package com.example.pkemonapipokedex.data.response

import com.google.gson.annotations.SerializedName

data class InformationPokemonResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("moves") val lisMoves: List<Moves>,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("types") val types: List<Types>,
)

data class Moves(
    @SerializedName("move") val move: Move,
)

data class Move(
    @SerializedName("name") val name: String,
)

data class Sprites(
    @SerializedName("front_default") val imageToolbar: String,
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
