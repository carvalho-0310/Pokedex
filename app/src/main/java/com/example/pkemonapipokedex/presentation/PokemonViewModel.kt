package com.example.pkemonapipokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _namesPokemon = MutableLiveData<List<String>>()
    val namesPokemon: LiveData<List<String>> = _namesPokemon

    private val _listPokemon = MutableLiveData<List<InformationPokemon>>()
    val listPokemon: LiveData<List<InformationPokemon>> = _listPokemon

    fun getNamesPokemon() {
        CoroutineScope(Dispatchers.Default).launch {
           _namesPokemon.postValue( repository.getListPokemon(limit, offset).names)
        }
        getPokemonInformation()
    }

    fun getPokemonInformation() {
        CoroutineScope(Dispatchers.Default).launch {
            val pokemons = mutableListOf<InformationPokemon>()
            _namesPokemon.value?.forEach {
                val pokemon = withContext(Dispatchers.Default) {
                    repository.getInformationPokemon(it)
                }
                pokemons.add(pokemon)
            }
            _listPokemon.postValue(pokemons)
        }
    }
}
