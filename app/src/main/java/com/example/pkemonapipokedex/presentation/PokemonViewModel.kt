package com.example.pkemonapipokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkemonapipokedex.data.repository.PokemonRepositoryImpl
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.presentation.PokemonViewModel.Response.ResponseMainViewFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val offset = 0
    private val limit = 15

    private var namesPokemon = listOf<String>()

    private val _listPokemon = MutableLiveData<ResponseMainViewFlow>()
    val listPokemon: LiveData<ResponseMainViewFlow> = _listPokemon

    private val _aciton = MutableLiveData<Animeitio>()
    val aciton: LiveData<Animeitio> = _aciton

    fun getNamesPokemon() {
        viewModelScope.launch {
                namesPokemon = repository.getListPokemon(limit, offset).names
                getPokemonInformation()
        }
    }

    private fun getPokemonInformation() {
        viewModelScope.launch {
            val listPokemonInformation = mutableListOf<InformationPokemon>()
            namesPokemon.forEach {
                val pokemon = withContext(Dispatchers.Default) {
                    repository.getInformationPokemon(it)
                }
                listPokemonInformation.add(pokemon)
            }
            val response = ResponseMainViewFlow(
                listPokemonInformation,
                loading = false,
                view = true
            )
            _listPokemon.postValue(response)
        }
    }

    fun startAnimaision(){
        _aciton.postValue(Animeitio.Start)
    }

    sealed class Animeitio{
        object Start : Animeitio()
    }

    sealed class Response{
        data class ResponseMainViewFlow(
            val listPokemon: List<InformationPokemon>,
            val loading: Boolean,
            val view: Boolean
        ): Response()
    }
}
