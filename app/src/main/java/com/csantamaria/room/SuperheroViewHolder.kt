package com.csantamaria.room

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.csantamaria.room.database.entities.ListEntity
import com.csantamaria.room.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemResponse: ListEntity, navigateToDetailActivity: (String) -> Unit) {
        binding.tvSuperheroName.text = superheroItemResponse.name
        Picasso.get().load(superheroItemResponse.image).into(binding.ivSuperhero)
        binding.root.setOnClickListener {
            navigateToDetailActivity(superheroItemResponse.id.toString())
        }
    }

}