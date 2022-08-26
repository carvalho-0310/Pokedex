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

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View, pokemonFragment: ListPokemonFragment) :
        RecyclerView.ViewHolder(itemView) {

        private val imagePokemon: AppCompatImageView = itemView.findViewById(R.id.image_pokemon)
        private val idPokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.id_pokemon)
        private val namePokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.name_pokemon)

        fun binding(pokemon: InformationPokemon) {
            Glide.with(imagePokemon)
                .load(pokemon.sprites)
                .into(imagePokemon)
            idPokemon.text =
                itemView.resources.getString(R.string.id_pokemon, pokemon.id.toString())
            namePokemon.text = itemView.resources.getString(R.string.name_pokemon, pokemon.name)

        }

//        private fun setBackgroundColorType(card: CardView, type: TypePossible) {
//            when (type) {
//                TypePossible.BUG -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.bug))
//                TypePossible.DARK -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.dark))
//                TypePossible.DRAGON -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.dragon))
//                TypePossible.ELECTRIC -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.electric))
//                TypePossible.FAIRY -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.fairy))
//                TypePossible.FIGHTING -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.fighting))
//                TypePossible.FIRE -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.fire))
//                TypePossible.FLYING -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.flying))
//                TypePossible.GHOST -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.ghost))
//                TypePossible.GRASS -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.grass))
//                TypePossible.GROUND -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.ground))
//                TypePossible.ICE -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.ice))
//                TypePossible.NORMAL -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.normal))
//                TypePossible.POISON -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.poison))
//                TypePossible.PSYCHIC -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.psychic))
//                TypePossible.ROCK -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.rock))
//                TypePossible.STEEL -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.steel))
//                TypePossible.WATER -> card.setCardBackgroundColor(ContextCompat.getColor(card.context ,R.color.water))
//            }
//        }

        init {
            itemView.setOnClickListener {
                pokemonFragment.openInformation()
            }
        }
    }
}
