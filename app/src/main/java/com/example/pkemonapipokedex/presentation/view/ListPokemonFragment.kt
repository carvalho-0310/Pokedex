package com.example.pkemonapipokedex.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.presentation.adapter.ListPokemonAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListPokemonFragment : MainViewModel() {

    private lateinit var rvPokemon: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list_pokemons, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPokemon = view.findViewById(R.id.rv_pokemon)

        setObserve()
    }

    private fun setObserve() {
        super.mainViewModel.listPokemon.observe(viewLifecycleOwner) {
            rvPokemon.adapter = ListPokemonAdapter(this, it.listPokemon)
        }
    }

    fun openInformation(pokemon: InformationPokemon) {

        val selectedPokemon = ListPokemonFragmentDirections.goToInformation(pokemon)
        mainViewModel.startAnimation()
        MainScope().launch {
            delay(1250)
            findNavController().navigate(selectedPokemon)
        }
    }
}
