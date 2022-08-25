package com.example.pkemonapipokedex.presentation.view

import androidx.fragment.app.Fragment
import com.example.pkemonapipokedex.presentation.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class MainViewModel  : Fragment(){

    protected val mainViewModel : PokemonViewModel by sharedViewModel()
}