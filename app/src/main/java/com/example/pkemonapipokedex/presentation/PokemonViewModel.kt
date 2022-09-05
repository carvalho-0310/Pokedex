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
    private val limit = 20

    private var namesPokemon = mutableListOf<String>()
    private val listPokemonInformation = mutableListOf<InformationPokemon>()

    private val _listPokemon = MutableLiveData<ResponseMainViewFlow>()
    val listPokemon: LiveData<ResponseMainViewFlow>
        get() = _listPokemon

    private val _acton = MutableLiveData<ActionView>()
    val acton: LiveData<ActionView>
        get() = _acton

    fun requestNamesPokemon() {
        viewModelScope.launch {
            kotlin.runCatching {
                namesPokemon = repository.getListPokemon(limit, offset).names.toMutableList()
            }.onSuccess {
                requestInformation()
            }.onFailure {
                _listPokemon.postValue(
                    ResponseMainViewFlow(
                        listPokemonInformation,
                        loading = false,
                        toolbar = true,
                        error = true
                    )
                )
            }
        }
    }

    private fun requestInformation() {
        viewModelScope.launch {
            listPokemonInformation.clear()
            kotlin.runCatching {
                namesPokemon.forEach {
                    val pokemon = withContext(Dispatchers.Default) {
                        repository.getInformationPokemon(it)
                    }
                    listPokemonInformation.add(pokemon)
                }
            }.onSuccess {
                _listPokemon.postValue(
                    ResponseMainViewFlow(
                        listPokemonInformation,
                        loading = false,
                        toolbar = true,
                        error = false
                    )
                )
            }.onFailure {
                _listPokemon.postValue(
                    ResponseMainViewFlow(
                        listPokemonInformation,
                        loading = false,
                        toolbar = true,
                        error = true
                    )
                )
            }
        }
    }

    fun startAnimation() {
        _acton.postValue(ActionView.Animation)
        showToolbar(false)
    }

    fun showToolbar(visibility: Boolean) {
        _listPokemon.postValue(
            ResponseMainViewFlow(
                listPokemonInformation,
                loading = false,
                toolbar = visibility,
                error = false
            )
        )
    }

    fun onClickQuit() {
        _acton.postValue(ActionView.Finish)
    }

    fun onClickTryAgain() {
        namesPokemon.clear()
        requestNamesPokemon()
    }


    sealed class ActionView {
        object Animation : ActionView()
        object Finish : ActionView()
    }

    sealed class Response {
        data class ResponseMainViewFlow(
            val listPokemon: List<InformationPokemon>,
            val loading: Boolean,
            val toolbar: Boolean,
            val error: Boolean
        ) : Response()
    }
}
