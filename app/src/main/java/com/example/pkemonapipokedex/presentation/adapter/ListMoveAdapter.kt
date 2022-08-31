package com.example.pkemonapipokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.InformationMove

class ListMoveAdapter (
    private val list: List<InformationMove>) :
    RecyclerView.Adapter<ListMoveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListMoveAdapter.ViewHolder(
            layoutInflater.inflate(R.layout.model_move, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.move_title)
        private val description = itemView.findViewById<TextView>(R.id.move_description)
        private val power = itemView.findViewById<TextView>(R.id.move_power)
        private val pp = itemView.findViewById<TextView>(R.id.move_pp)

        fun binding(move: InformationMove) {
            title.text = move.name
            description.text = move.effect.toString()
            power.text = itemView.resources.getString(R.string.power, move.power.toString())
            pp.text = itemView.resources.getString(R.string.pp, move.pp.toString())
        }
    }
}
