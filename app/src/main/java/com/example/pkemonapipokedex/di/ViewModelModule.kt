package com.example.pkemonapipokedex.di

import com.example.pkemonapipokedex.data.mapper.PageMapperImpl
import com.example.pkemonapipokedex.data.mapper.PokemonMapperImpl
import com.example.pkemonapipokedex.data.repository.PokemonApi
import com.example.pkemonapipokedex.data.repository.PokemonRepositoryImpl
import com.example.pkemonapipokedex.presentation.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelPokemon = module {
    factory { PokemonMapperImpl() }
    factory { PageMapperImpl() }
    factory<PokemonApi> { get<Retrofit>().create(PokemonApi::class.java) }
    viewModel {
        PokemonViewModel(PokemonRepositoryImpl(get(), get(), get()))
    }
}