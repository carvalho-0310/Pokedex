package com.example.pkemonapipokedex.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
    private lateinit var adapter: ListPokemonAdapter

    private var validClick = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list_pokemons, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPokemon = view.findViewById(R.id.rv_pokemon)
        adapter = ListPokemonAdapter(this)
        rvPokemon.adapter = adapter

        rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(2)) {
                    mainViewModel.onScrollFinal()
                }
            }
        })
        setObserve()
    }

    private fun setObserve() {
        super.mainViewModel.listPokemon.observe(viewLifecycleOwner) {
            rvPokemon.isVisible = it.pokemon
            setListAdapter(it.listPokemon)
        }
    }

    private fun setListAdapter(list: List<InformationPokemon>) {
        adapter.setList(list)

    }

    fun openInformation(pokemon: InformationPokemon) {
        if (validClick) {
            validClick = false
            val selectedPokemon = ListPokemonFragmentDirections.goToInformation(pokemon)
            mainViewModel.startAnimation()
            MainScope().launch {
                delay(1250)
                findNavController().navigate(selectedPokemon)
                validClick = true
            }
        }
    }
}
