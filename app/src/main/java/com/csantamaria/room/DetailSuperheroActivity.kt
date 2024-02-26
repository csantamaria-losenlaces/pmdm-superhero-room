package com.csantamaria.room

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.csantamaria.room.SuperheroListActivity.Companion.EXTRA_ID
import com.csantamaria.room.database.SuperheroDatabase
import com.csantamaria.room.database.entities.DetailEntity
import com.csantamaria.room.database.entities.ListEntity
import com.csantamaria.room.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperheroBinding
    private lateinit var room: SuperheroDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        room = Room.databaseBuilder(this, SuperheroDatabase::class.java, "superheros").build()

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)

    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroList: ListEntity = room.listDao().getSuperhero(id)
            Log.i("Room", "Estoy buscando el ID $id")
            val superheroDetails: DetailEntity = room.detailsDao().getSuperheroDetails(id)
            runOnUiThread { createUI(superheroList, superheroDetails) }
        }
    }

    private fun createUI(superheroList:ListEntity, superheroDetails: DetailEntity) {
        Picasso.get().load(superheroList.image).into(binding.ivSuperhero)

        binding.tvSuperheroName.text = superheroList.name
        binding.tvSuperheroRealName.text = superheroDetails.fullName
        binding.tvPublisher.text = superheroDetails.publisher

        prepareStats(superheroDetails)
    }

    private fun prepareStats(superheroDetails: DetailEntity) {
        updateHeight(binding.viewIntelligence, superheroDetails.intelligence)
        updateHeight(binding.viewStrength, superheroDetails.strength)
        updateHeight(binding.viewSpeed, superheroDetails.speed)
        updateHeight(binding.viewDurability, superheroDetails.durability)
        updateHeight(binding.viewPower, superheroDetails.power)
        updateHeight(binding.viewCombat, superheroDetails.combat)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        if (stat != "null") {
            params.height = pxToDp(stat.toFloat())
        } else {
            params.height = 0
        }
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

}