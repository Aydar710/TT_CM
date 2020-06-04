package com.aydar.tt_cm.featurepersons.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aydar.tt_cm.R
import com.aydar.tt_cm.data.Person
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_spot.view.*

class CardStackAdapter(
    private var spots: List<Person> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PersonViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = spots[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<Person>) {
        this.spots = spots
    }

    fun getSpots(): List<Person> {
        return spots
    }

    class PersonViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(person: Person) {
            with(view) {
                item_name.text = "${person.id}. ${person.name}"
                item_city.text = person.city

                Glide.with(item_image)
                    .load(person.url)
                    .into(item_image)
            }
        }
    }

}