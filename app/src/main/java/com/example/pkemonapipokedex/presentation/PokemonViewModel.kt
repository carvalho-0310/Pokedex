package com.example.pkemonapipokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkemonapipokedex.data.repository.PokemonRepositoryImpl
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val offset = 0
    private val limit = 150

    private var namesPokemon = listOf<String>()

    private val _listPokemon = MutableLiveData<ResponseMainViewFlow>()
    val listPokemon: LiveData<ResponseMainViewFlow> = _listPokemon

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
            val response =  ResponseMainViewFlow(
                listPokemonInformation,
                false,
                true
            )
            _listPokemon.postValue(response)
        }
    }
}

data class ResponseMainViewFlow(
    val listPokemons: List<InformationPokemon>,
    val loading: Boolean,
    val view: Boolean
)
