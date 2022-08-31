package com.example.pkemonapipokedex.presentation.view

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.presentation.PokemonViewModel
import com.example.pkemonapipokedex.presentation.PokemonViewModel.Response.ResponseMainViewFlow
import com.example.pkemonapipokedex.presentation.startAnimation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: View
    private lateinit var pokeBoll: View
    private lateinit var loading: ProgressBar
    private lateinit var animationExplosion: Animation

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loading = findViewById(R.id.loading)
        pokeBoll = findViewById(R.id.pokeboll)
        toolbar = findViewById(R.id.toolbar_id)

        setViewModel()
        setObserves()
        setAnimation()
    }

    private fun setViewModel(){
        viewModel.requestNamesPokemon()
    }

    private fun setObserves(){
        viewModel.acton.observe(this) {
            startExplosion()
        }

        viewModel.listPokemon.observe(this) {
            setVisibility(it)
        }
    }

    private fun setAnimation() {
        animationExplosion =
            AnimationUtils.loadAnimation(this, R.anim.bokeboll_explosion_animation)
                .apply {
                    duration = 2500
                    interpolator = AccelerateDecelerateInterpolator()
                }
    }

    private fun setVisibility(response: ResponseMainViewFlow) {
        loading.isVisible = response.loading
        toolbar.isVisible = true
    }

    private fun startExplosion() {
        pokeBoll.startAnimation(animationExplosion) {
            pokeBoll.isVisible = false
        }
        toolbar.isVisible = !toolbar.isVisible
    }
}
