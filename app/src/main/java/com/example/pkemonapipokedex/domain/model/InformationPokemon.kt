package com.example.pkemonapipokedex.domain.model

data class InformationPokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: String,
    val types: List<TypePossible>,
)

enum class TypePossible(type: String) {
    BUG("bug"),
    DARK ("dark"),
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
    NORMAL ("normal"),
    POISON( "poison"),
    PSYCHIC( "psychic"),
    ROCK("rock"),
    STEEL("steel"),
    WATER("water"),
}
