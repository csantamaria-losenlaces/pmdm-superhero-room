package com.csantamaria.room

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.csantamaria.room.SuperheroListActivity.Companion.EXTRA_ID
import com.csantamaria.room.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperheroBinding

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =
                getRetrofit().create(ApiService::class.java).getSuperheroDetails(id)
            if (superheroDetail.body() != null) {
                runOnUiThread { createUI(superheroDetail.body()!!) }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createUI(superhero: SuperheroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)

        binding.tvSuperheroName.text = superhero.name
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
        prepareStats(superhero.powerstats)

        binding.tvConnections.text = formatConnections(superhero.connections.relatives)
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewCombat, powerstats.combat)
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

    private fun formatConnections(connections: String): String {
        val relativesArray: List<String> = connections.split("), ")
        var relativesFormatted = ""
        relativesArray.forEach {
            s -> relativesFormatted = relativesFormatted.plus("• $s)\n")
        }
        return relativesFormatted.dropLast(2)
    }*/

}