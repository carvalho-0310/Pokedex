package com.example.pkemonapipokedex.domain.repository

import com.example.pkemonapipokedex.domain.model.InformationPages
import com.example.pkemonapipokedex.domain.model.InformationPokemon

interface PokemonRepository {
    suspend fun getListPokemon(limit: Int) : InformationPages
    suspend fun getInformationPokemon(name :String) : InformationPokemon
}