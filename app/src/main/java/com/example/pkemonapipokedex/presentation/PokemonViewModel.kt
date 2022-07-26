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
) :ViewModel() {

    private val _pLiveData = MutableLiveData<InformationPokemon>()
    val pLiveData : LiveData<InformationPokemon>
    get() = _pLiveData

    fun getPokemonName(name: String = "193") {
        CoroutineScope(Dispatchers.Default).launch {
            val pokemon = withContext(Dispatchers.Default) {
                repository.getInformationPokemon(name)
            }
            _pLiveData.postValue(pokemon)
        }
    }

    fun hasIsType(pokemon: InformationPokemon, cast : Int): Boolean = pokemon.types.lastIndex >= cast


}