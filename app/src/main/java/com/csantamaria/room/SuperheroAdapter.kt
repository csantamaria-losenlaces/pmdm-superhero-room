package com.csantamaria.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csantamaria.room.database.dao.ListDao
import com.csantamaria.room.database.entities.ListEntity

class SuperheroAdapter(
    private var superheroList: MutableList<ListEntity>,
    private val navigateToDetailActivity: (String) -> Unit
) :
    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(newList: ListDao) {
        superheroList.clear()
        superheroList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(superheroList[position], navigateToDetailActivity)
    }

    override fun getItemCount() = superheroList.size

}