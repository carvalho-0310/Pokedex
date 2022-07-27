package com.example.pkemonapipokedex.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imagePokemon: AppCompatImageView = itemView.findViewById(R.id.image_pokemon)
        val idPokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.id_pokemon)
        val namePokemon: AppCompatCheckedTextView = itemView.findViewById(R.id.name_pokemon)

        val separatorOne: View = itemView.findViewById(R.id.v_0ne)
        val separatorTwo: View = itemView.findViewById(R.id.v_two)
        val separatorTree: View = itemView.findViewById(R.id.v_tree)

        val backgroundColor: ConstraintLayout = itemView.findViewById(R.id.background_color)

        val typeOne: CardView = itemView.findViewById(R.id.type_1)
        val textOne: AppCompatCheckedTextView = itemView.findViewById(R.id.text_1)

        val typeTwo: CardView = itemView.findViewById(R.id.type_2)
        val textTwo: AppCompatCheckedTextView = itemView.findViewById(R.id.text_2)

        val typeTree: CardView = itemView.findViewById(R.id.type_3)
        val textTree = itemView.findViewById<AppCompatCheckedTextView>(R.id.text_3)

        val typeFour: CardView = itemView.findViewById(R.id.type_4)
        val textFour: AppCompatCheckedTextView = itemView.findViewById(R.id.text_4)


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
//                setBackgroundColor(pokemon.types[0])
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
                TypePossible.BUG -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.bug))
                TypePossible.DARK -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.dark))
                TypePossible.DRAGON -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.dragon))
                TypePossible.ELECTRIC -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.electric))
                TypePossible.FAIRY -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.fairy))
                TypePossible.FIGHTING -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.fighting))
                TypePossible.FIRE -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.fire))
                TypePossible.FLYING -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.flying))
                TypePossible.GHOST -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.ghost))
                TypePossible.GRASS -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.grass))
                TypePossible.GROUND -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.ground))
                TypePossible.ICE -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.ice))
                TypePossible.NORMAL -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.normal))
                TypePossible.POISON -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.poison))
                TypePossible.PSYCHIC -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.psychic))
                TypePossible.ROCK -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.rock))
                TypePossible.STEEL -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.steel))
                TypePossible.WATER -> card.setCardBackgroundColor(itemView.resources.getColor(R.color.water))
            }
        }

//        private fun setBackgroundColor(type: TypePossible) {
//            when (type) {
//                TypePossible.BUG -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.bugOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.bug))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.bug))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.bug))
//                }
//                TypePossible.DARK -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.darkOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.dark))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.dark))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.dark))
//                }
//                TypePossible.DRAGON -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.dragonOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.dragon))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.dragon))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.dragon))
//                }
//                TypePossible.ELECTRIC -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.electricOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.electric))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.electric))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.electric))
//                }
//                TypePossible.FAIRY -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.fairyOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.fairy))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.fairy))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.fairy))
//                }
//                TypePossible.FIGHTING -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.fightingOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.fighting))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.fighting))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.fighting))
//                }
//                TypePossible.FIRE -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.fireOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.fire))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.fire))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.fire))
//                }
//                TypePossible.FLYING -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.flyingOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.flying))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.flying))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.flying))
//                }
//                TypePossible.GHOST -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.ghostOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.ghost))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.ghost))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.ghost))
//                }
//                TypePossible.GRASS -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.grassOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.grass))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.grass))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.grass))
//                }
//                TypePossible.GROUND -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.groundOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.ground))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.ground))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.ground))
//                }
//                TypePossible.ICE -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.iceOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.ice))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.ice))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.ice))
//                }
//                TypePossible.NORMAL -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.normalOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.normal))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.normal))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.normal))
//                }
//                TypePossible.POISON -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.poisonOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.poison))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.poison))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.poison))
//                }
//                TypePossible.PSYCHIC -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.psychicOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.psychic))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.psychic))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.psychic))
//                }
//                TypePossible.ROCK -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.rockOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.rock))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.rock))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.rock))
//                }
//                TypePossible.STEEL -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.steelOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.steel))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.steel))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.steel))
//                }
//                TypePossible.WATER -> {
//                    backgroundColor.setBackgroundColor(itemView.resources.getColor(R.color.waterOptional))
//                    separatorOne.setBackgroundColor(itemView.resources.getColor(R.color.water))
//                    separatorTwo.setBackgroundColor(itemView.resources.getColor(R.color.water))
//                    separatorTree.setBackgroundColor(itemView.resources.getColor(R.color.water))
//                }
//            }
//        }

        private fun hasIsType(pokemon: InformationPokemon, cast: Int): Boolean =
            pokemon.types.lastIndex >= cast
    }
}
