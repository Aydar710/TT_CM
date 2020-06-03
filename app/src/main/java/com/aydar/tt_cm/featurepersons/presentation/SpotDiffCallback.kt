package com.aydar.tt_cm.featurepersons.presentation

import androidx.recyclerview.widget.DiffUtil
import com.aydar.tt_cm.data.Person

class SpotDiffCallback(
    private val old: List<Person>,
    private val new: List<Person>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == new[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == new[newPosition]
    }

}