package com.csantamaria.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.csantamaria.room.database.SuperheroDatabase
import com.csantamaria.room.database.entities.ListEntity
import kotlinx.coroutines.newCoroutineContext
import kotlin.coroutines.coroutineContext

class SuperheroAdapter(
    var superheroList: List<ListEntity> = emptyList(),
    private val navigateToDetailActivity: (Int) -> Unit
) :

    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(list: List<ListEntity>) {
        superheroList = list
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