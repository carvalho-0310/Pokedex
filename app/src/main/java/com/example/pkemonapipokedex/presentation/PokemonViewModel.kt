package com.example.pkemonapipokedex.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkemonapipokedex.data.repository.PokemonRepositoryImpl
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.presentation.PokemonViewModel.Response.ResponseMainViewFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class PokemonViewModel(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private var offset = 0
    private val limit = 20
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
        if (requestAvailable) {

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
                    requestAvailable = true
                }
            }
        }
    }

    private fun requestInformation() {
        viewModelScope.launch {
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
                        error = false,
                        pokemon = true
                    )
                )
                offset += limit
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

    fun onScrollFinal(){
        requestNamesPokemon()
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
            val pokemon: Boolean,
            val toolbar: Boolean,
            val error: Boolean
        ) : Response()
    }
}
