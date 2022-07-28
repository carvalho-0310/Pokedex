package com.example.pkemonapipokedex.presentation

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pkemonapipokedex.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var rvPokemon: RecyclerView
    private lateinit var loading: ProgressBar
    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPokemon = findViewById(R.id.rv_pokemon)
        loading = findViewById(R.id.loading)

        viewModel.getNamesPokemon()

        viewModel.listPokemon.observe(this) {
            rvPokemon.adapter = ListPokemonAdapter(it.listPokemons)
            setVisibility(it)
        }
    }

    private fun setVisibility(response: ResponseMainViewFlow){
        loading.isVisible = response.loading
        rvPokemon.isVisible = response.view
    }
}
