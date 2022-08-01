package com.example.pkemonapipokedex.presentation

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.presentation.PokemonViewModel.Response.ResponseMainViewFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var rvPokemon: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var pokeBoll: View
    private lateinit var animationExplosion: Animation
    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationExplosion =
            AnimationUtils.loadAnimation(this, R.anim.bokeboll_explosion_animation)
                .apply {
                    duration = 2500
                    interpolator = AccelerateDecelerateInterpolator()
                }

        rvPokemon = findViewById(R.id.rv_pokemon)
        loading = findViewById(R.id.loading)
        pokeBoll = findViewById(R.id.pokeboll)

        viewModel.getNamesPokemon()

        viewModel.listPokemon.observe(this) {
            rvPokemon.adapter = ListPokemonAdapter(this, it.listPokemon)
            setVisibility(it)
        }
    }

    private fun setVisibility(response: ResponseMainViewFlow){
        loading.isVisible = response.loading
        rvPokemon.isVisible = response.view
    }

    fun statExplosion() {
        pokeBoll.startAnimation(animationExplosion) {
            pokeBoll.isVisible = false
        }
    }
}
