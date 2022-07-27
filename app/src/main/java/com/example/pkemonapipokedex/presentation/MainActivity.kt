package com.example.pkemonapipokedex.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pkemonapipokedex.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var  rvPokemon : RecyclerView
    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPokemon = findViewById(R.id.rv_pokemon)

        viewModel.getNamesPokemon()

        viewModel.namesPokemon.observe(this){
            viewModel.getPokemonInformation()
        }

        viewModel.listPokemon.observe(this){
            rvPokemon.adapter = ListPokemonAdapter(it)
        }
    }
}
