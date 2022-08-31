package com.example.pkemonapipokedex.domain.repository

import com.example.pkemonapipokedex.domain.model.InformationMove
import com.example.pkemonapipokedex.domain.model.InformationPages
import com.example.pkemonapipokedex.domain.model.InformationPokemon

interface PokemonRepository {
    suspend fun getListPokemon(limit: Int,offset:Int) : InformationPages
    suspend fun getInformationPokemon(name :String) : InformationPokemon
    suspend fun requestMoves(move :String) : InformationMove
}
