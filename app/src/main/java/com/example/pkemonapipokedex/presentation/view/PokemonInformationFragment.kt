package com.example.pkemonapipokedex.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.InformationMove
import com.example.pkemonapipokedex.presentation.adapter.ListMoveAdapter
import com.example.pkemonapipokedex.presentation.adapter.ListTypeAdapter

class PokemonInformationFragment : MainViewModel() {

    private val pokemon: PokemonInformationFragmentArgs by navArgs()

    private lateinit var weight: AppCompatTextView
    private lateinit var height: AppCompatTextView
    private lateinit var toolbar: LinearLayoutCompat
    private lateinit var toolbarText: AppCompatTextView
    private lateinit var toolbarImage: AppCompatImageView
    private lateinit var pokemonPresentation: AppCompatImageView
    private lateinit var typeAdapter: RecyclerView
    private lateinit var moveAdapter: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pokemon_information, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weight = view.findViewById(R.id.weight_id)
        height = view.findViewById(R.id.height_id)
        toolbar = view.findViewById(R.id.toolbar_information_id)
        toolbarText = view.findViewById(R.id.text_toolbar_information)
        toolbarImage = view.findViewById(R.id.image_toolbar_information)
        pokemonPresentation = view.findViewById(R.id.image_pokemon_Presentation)
        typeAdapter =  view.findViewById(R.id.recycler_view_type)
        moveAdapter =  view.findViewById(R.id.recycler_view_moves)

        super.mainViewModel.requestMoves(pokemon.pokemon.listMoves)

        super.mainViewModel.listMoves.observe(viewLifecycleOwner){
            setAdapterMoves(it.moves)
        }

        typeAdapter.adapter = ListTypeAdapter(pokemon.pokemon.types)


        toolbar.isVisible = true

        Glide.with(pokemonPresentation)
            .load(pokemon.pokemon.sprites)
            .into(pokemonPresentation)

        Glide.with(toolbarImage)
            .load(pokemon.pokemon.spritesToolbar)
            .into(toolbarImage)

        weight.text = view.resources.getString(R.string.weight, pokemon.pokemon.weight.toString())
        height.text = view.resources.getString(R.string.height, pokemon.pokemon.height.toString())
        toolbarText.text = view.resources.getString(R.string.toolbar_information,pokemon.pokemon.id.toString(),pokemon.pokemon.name)
    }

    private  fun setAdapterMoves(informationMove: List<InformationMove>){
        moveAdapter.adapter = ListMoveAdapter(informationMove)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.startAnimation()
        toolbar.isVisible = false
    }
}