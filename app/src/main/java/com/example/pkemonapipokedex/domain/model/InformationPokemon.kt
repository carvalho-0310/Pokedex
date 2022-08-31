package com.example.pkemonapipokedex.domain.model

import java.io.Serializable
import java.math.BigDecimal

data class InformationPokemon(
    val id: Int,
    val name: String,
    val height: BigDecimal,
    val weight: BigDecimal,
    val listMoves : List<String>,
    val spritesToolbar: String,
    val sprites: String,
    val types: List<TypePossible>,
) : Serializable

enum class TypePossible(type: String) {
    BUG("bug"),
    DARK("dark"),
    DRAGON("dragon"),
    ELECTRIC("electric"),
    FAIRY("fairy"),
    FIGHTING("fighting"),
    FIRE("fire"),
    FLYING("flying"),
    GHOST("ghost"),
    GRASS("grass"),
    GROUND("ground"),
    ICE("ice"),
    NORMAL("normal"),
    POISON("poison"),
    PSYCHIC("psychic"),
    ROCK("rock"),
    STEEL("steel"),
    WATER("water"),
}
