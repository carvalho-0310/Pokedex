package com.example.pkemonapipokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkemonapipokedex.data.repository.PokemonRepositoryImpl
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.presentation.PokemonViewModel.Response.ResponseMainViewFlow
import kotlinx.coroutines.*

const val LAST_POKEMON = 493

class PokemonViewModel(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private var offset = 0
    private var limit = 20
    private var requestAvailable = true

    private var namesPokemon = mutableListOf<String>()
    private val listPokemonInformation = mutableListOf<InformationPokemon>()

    private val _listPokemon = MutableLiveData<ResponseMainViewFlow>()
    val listPokemon: LiveData<ResponseMainViewFlow>
        get() = _listPokemon

    private val _acton = MutableLiveData<ActionView>()
    val acton: LiveData<ActionView>
        get() = _acton

    fun requestNamesPokemon() {
        if (requestAvailable && offset != LAST_POKEMON) {

            requestAvailable = false

            _listPokemon.postValue(
                ResponseMainViewFlow(
                    listPokemonInformation,
                    loading = true,
                    toolbar = true,
                    error = false,
                    pokemon = true
                )
            )
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
                            error = true,
                            pokemon = true
                        )
                    )
                }
            }
        }
    }

    private fun requestInformation() {
        viewModelScope.launch {
            kotlin.runCatching {
                listPokemonInformation.addAll(namesPokemon.map {
                    async {
                        repository.getInformationPokemon(it)
                    }
                }.awaitAll())
            }.onSuccess {
                _listPokemon.postValue(
                    ResponseMainViewFlow(
                        listPokemonInformation,
                        loading = false,
                        toolbar = true,
                        error = false,
                        pokemon = true
                    )
                )
                hasLastPokemon()

                requestAvailable = true
            }.onFailure {
                _listPokemon.postValue(
                    ResponseMainViewFlow(
                        listPokemonInformation,
                        loading = false,
                        toolbar = true,
                        error = true,
                        pokemon = true
                    )
                )
            }
        }
    }

    private fun hasLastPokemon() {
        offset += limit
        if ((offset + limit) >= LAST_POKEMON) {
            limit = LAST_POKEMON - offset
        }
    }

    fun startAnimation() {
        _acton.postValue(ActionView.Animation)
        MainScope().launch {
            delay(1250)
            showToolbar(false)
        }
    }

    fun showToolbar(visibility: Boolean) {
        _listPokemon.postValue(
            ResponseMainViewFlow(
                listPokemonInformation,
                loading = false,
                toolbar = visibility,
                error = false,
                pokemon = true
            )
        )
    }

    fun onScrollFinal() {
        requestNamesPokemon()
    }

    fun onClickQuit() {
        _acton.postValue(ActionView.Finish)
    }

    fun onClickTryAgain() {
        requestAvailable = true
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
            val pokemon: Boolean,
            val toolbar: Boolean,
            val error: Boolean
        ) : Response()
    }
}
