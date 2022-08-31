package com.example.pkemonapipokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.presentation.view.ListPokemonFragment

class ListPokemonAdapter(
    private val pokemonFragment: ListPokemonFragment,
    private val list: List<InformationPokemon>
) :
    RecyclerView.Adapter<ListPokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.model_card_pokemon, parent, false), pokemonFragment
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View, pokemonFragment: ListPokemonFragment) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var pokemon: InformationPokemon

        private val imagePokemon: AppCompatImageView = itemView.findViewById(R.id.image_pokemon)
        private val idPokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.id_pokemon)
        private val namePokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.name_pokemon)

        fun binding(pokemon: InformationPokemon) {
            this.pokemon = pokemon
            Glide.with(imagePokemon)
                .load(pokemon.sprites)
                .into(imagePokemon)
            idPokemon.text =
                itemView.resources.getString(R.string.id_pokemon, pokemon.id.toString())
            namePokemon.text = itemView.resources.getString(R.string.name_pokemon, pokemon.name)

        }

        init {
            itemView.setOnClickListener {
                pokemonFragment.openInformation(pokemon)
            }
        }
    }
}
