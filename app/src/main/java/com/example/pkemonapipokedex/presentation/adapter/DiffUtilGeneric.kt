package com.example.pkemonapipokedex.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilGeneric<T : DiffUtilGeneric.Compare<in T>>(var oldList: List<T>, var newList: List<T>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].isEqual(oldList[oldItemPosition])
    }

    interface Compare<T> {
        val id: Int
        fun isEqual(o: T): Boolean
    }
}
