package com.example.pkemonapipokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkemonapipokedex.data.repository.PokemonRepositoryImpl
import com.example.pkemonapipokedex.domain.model.InformationMove
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.presentation.PokemonViewModel.MovesInformation.*
import com.example.pkemonapipokedex.presentation.PokemonViewModel.Response.ResponseMainViewFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val offset = 0
    private val limit = 151

    private var namesPokemon = listOf<String>()

    private val _listPokemon = MutableLiveData<ResponseMainViewFlow>()
    val listPokemon: LiveData<ResponseMainViewFlow>
        get() = _listPokemon

    private val _acton = MutableLiveData<ActionView>()
    val acton: LiveData<ActionView>
        get() = _acton

    private val _listMoves = MutableLiveData<MovesFlow>()
    val listMoves: LiveData<MovesFlow>
        get() = _listMoves

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

    fun requestMoves(moves: List<String>) {
        val listMoveInformation = mutableListOf<InformationMove>()
        viewModelScope.launch {
            moves.forEach {
                val move = withContext(Dispatchers.Default) {
                    repository.requestMoves(it)
                }
                listMoveInformation.add(move)
            }
            val response = MovesFlow(
                listMoveInformation
            )
            _listMoves.postValue(response)
        }
    }

    fun startAnimation() {
        _acton.postValue(ActionView.Start)
    }

    sealed class ActionView {
        object Start : ActionView()
    }

    sealed class Response {
        data class ResponseMainViewFlow(
            val listPokemon: List<InformationPokemon>,
            val loading: Boolean,
            val view: Boolean
        ) : Response()
    }

    sealed class MovesInformation {
        data class MovesFlow(
            val moves: List<InformationMove>
        )
    }
}
