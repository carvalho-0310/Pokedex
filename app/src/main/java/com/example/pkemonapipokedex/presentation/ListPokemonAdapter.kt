package com.example.pkemonapipokedex.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.domain.model.TypePossible

private const val VISIBLE = 0
private const val GONE = 8

class ListPokemonAdapter(private val list: List<InformationPokemon>) :
    RecyclerView.Adapter<ListPokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(R.layout.model_main_activity, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imagePokemon: AppCompatImageView = itemView.findViewById(R.id.image_pokemon)
        private val idPokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.id_pokemon)
        private val namePokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.name_pokemon)

        private val typeOne: CardView = itemView.findViewById(R.id.type_1)
        private val textOne: AppCompatCheckedTextView = itemView.findViewById(R.id.text_1)

        private val typeTwo: CardView = itemView.findViewById(R.id.type_2)
        private val textTwo: AppCompatCheckedTextView = itemView.findViewById(R.id.text_2)

        private val typeTree: CardView = itemView.findViewById(R.id.type_3)
        private val textTree: AppCompatCheckedTextView = itemView.findViewById(R.id.text_3)

        private val typeFour: CardView = itemView.findViewById(R.id.type_4)
        private val textFour: AppCompatCheckedTextView = itemView.findViewById(R.id.text_4)


        fun binding(pokemon: InformationPokemon) {
            typeOne.visibility = GONE
            typeTwo.visibility = GONE
            typeTree.visibility = GONE
            typeFour.visibility = GONE

            Glide.with(imagePokemon)
                .load(pokemon.sprites)
                .into(imagePokemon)
            idPokemon.text =
                itemView.resources.getString(R.string.id_pokemon, pokemon.id.toString())
            namePokemon.text = itemView.resources.getString(R.string.name_pokemon, pokemon.name)

            hasTypeOne(pokemon)
            hasTypeTwo(pokemon)
            hasTypeTree(pokemon)
            hasTypeFour(pokemon)
        }

        private fun hasTypeOne(pokemon: InformationPokemon) {
            if (hasIsType(pokemon, 0)) {
                typeOne.visibility = VISIBLE
                textOne.text = pokemon.types[0].name
                setBackgroundColorType(typeOne, pokemon.types[0])
            }
        }

        private fun hasTypeTwo(pokemon: InformationPokemon) {
            if (hasIsType(pokemon, 1)) {
                typeTwo.visibility = VISIBLE
                textTwo.text = pokemon.types[1].name
                setBackgroundColorType(typeTwo, pokemon.types[1])
            }
        }

        private fun hasTypeTree(pokemon: InformationPokemon) {
            if (hasIsType(pokemon, 2)) {
                typeTree.visibility = VISIBLE
                textTree.text = pokemon.types[2].name
                setBackgroundColorType(typeTree, pokemon.types[2])
            }
        }

        private fun hasTypeFour(pokemon: InformationPokemon) {
            if (hasIsType(pokemon, 3)) {
                typeFour.visibility = VISIBLE
                textFour.text = pokemon.types[3].name
                setBackgroundColorType(typeFour, pokemon.types[3])
            }
        }

        private fun setBackgroundColorType(card: CardView, type: TypePossible) {
            when (type) {
                TypePossible.BUG -> card.setCardBackgroundColor(card.resources.getColor(R.color.bug))
                TypePossible.DARK -> card.setCardBackgroundColor(card.resources.getColor(R.color.dark))
                TypePossible.DRAGON -> card.setCardBackgroundColor(card.resources.getColor(R.color.dragon))
                TypePossible.ELECTRIC -> card.setCardBackgroundColor(card.resources.getColor(R.color.electric))
                TypePossible.FAIRY -> card.setCardBackgroundColor(card.resources.getColor(R.color.fairy))
                TypePossible.FIGHTING -> card.setCardBackgroundColor(card.resources.getColor(R.color.fighting))
                TypePossible.FIRE -> card.setCardBackgroundColor(card.resources.getColor(R.color.fire))
                TypePossible.FLYING -> card.setCardBackgroundColor(card.resources.getColor(R.color.flying))
                TypePossible.GHOST -> card.setCardBackgroundColor(card.resources.getColor(R.color.ghost))
                TypePossible.GRASS -> card.setCardBackgroundColor(card.resources.getColor(R.color.grass))
                TypePossible.GROUND -> card.setCardBackgroundColor(card.resources.getColor(R.color.ground))
                TypePossible.ICE -> card.setCardBackgroundColor(card.resources.getColor(R.color.ice))
                TypePossible.NORMAL -> card.setCardBackgroundColor(card.resources.getColor(R.color.normal))
                TypePossible.POISON -> card.setCardBackgroundColor(card.resources.getColor(R.color.poison))
                TypePossible.PSYCHIC -> card.setCardBackgroundColor(card.resources.getColor(R.color.psychic))
                TypePossible.ROCK -> card.setCardBackgroundColor(card.resources.getColor(R.color.rock))
                TypePossible.STEEL -> card.setCardBackgroundColor(card.resources.getColor(R.color.steel))
                TypePossible.WATER -> card.setCardBackgroundColor(card.resources.getColor(R.color.water))
            }
        }

        private fun hasIsType(pokemon: InformationPokemon, cast: Int): Boolean =
            pokemon.types.lastIndex >= cast
    }
}