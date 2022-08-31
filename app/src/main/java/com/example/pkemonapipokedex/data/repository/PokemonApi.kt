package com.example.pkemonapipokedex.data.repository

import com.example.pkemonapipokedex.data.response.InformationMoveResponse
import com.example.pkemonapipokedex.data.response.InformationPokemonResponse
import com.example.pkemonapipokedex.data.response.PagesPokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getListPokemon(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): PagesPokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getInformationPokemon(
        @Path("name") name: String
    ): InformationPokemonResponse

    @GET("move/{move}/")
    suspend fun requestMoves(
        @Path("move") move: String
    ): InformationMoveResponse
}
