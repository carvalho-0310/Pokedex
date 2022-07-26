package com.example.pkemonapipokedex.data.repository

import com.example.pkemonapipokedex.data.mapper.PageMapperImpl
import com.example.pkemonapipokedex.data.mapper.PokemonMapperImpl
import com.example.pkemonapipokedex.domain.model.InformationPages
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val OFFSET = 20

class PokemonRepositoryImpl(
    private val mapperPokemon: PokemonMapperImpl,
    private val mapperPages: PageMapperImpl,
    private val api: PokemonApi
) : PokemonRepository {

    override suspend fun getListPokemon(limit: Int): InformationPages =
        withContext(Dispatchers.IO) { mapperPages.toDomain(api.getListPokemon(limit, OFFSET)) }


    override suspend fun getInformationPokemon(name: String): InformationPokemon =
        withContext(Dispatchers.IO) { mapperPokemon.toDomain(api.getInformationPokemon(name)) }
}