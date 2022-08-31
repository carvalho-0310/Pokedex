package com.example.pkemonapipokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.TypePossible
import com.google.android.material.button.MaterialButton

class ListTypeAdapter(private val list: List<TypePossible>) :
    RecyclerView.Adapter<ListTypeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTypeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListTypeAdapter.ViewHolder(
            layoutInflater.inflate(R.layout.model_types_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListTypeAdapter.ViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val card: MaterialButton = itemView.findViewById(R.id.type_view)

        fun binding(typePossible: TypePossible) {
            card.text = typePossible.name
            setBackground(typePossible)
        }


        private fun setBackground(type: TypePossible){
            when (type) {
                TypePossible.BUG -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.bug))
                TypePossible.DARK -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.dark))
                TypePossible.DRAGON -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.dragon))
                TypePossible.ELECTRIC -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.electric))
                TypePossible.FAIRY -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.fairy))
                TypePossible.FIGHTING -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.fighting))
                TypePossible.FIRE -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.fire))
                TypePossible.FLYING -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.flying))
                TypePossible.GHOST -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.ghost))
                TypePossible.GRASS -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.grass))
                TypePossible.GROUND -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.ground))
                TypePossible.ICE -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.ice))
                TypePossible.NORMAL -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.normal))
                TypePossible.POISON -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.poison))
                TypePossible.PSYCHIC -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.psychic))
                TypePossible.ROCK -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.rock))
                TypePossible.STEEL -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.steel))
                TypePossible.WATER -> card.setBackgroundColor(ContextCompat.getColor(card.context ,R.color.water))
            }
        }
    }
}
