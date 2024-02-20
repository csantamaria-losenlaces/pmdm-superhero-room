package com.csantamaria.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.csantamaria.room.database.SuperheroDatabase
import com.csantamaria.room.databinding.ActivitySuperheroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperheroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter

    private val room = Room.databaseBuilder(this, SuperheroDatabase::class.java, "superheros").build()
    private val listDao = room.getListDao()
    private val detailDao = room.getDetailDao()

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()

        initUI()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }
            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperheroAdapter { superheroId -> navigateToDetail(superheroId) }
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter
    }

    private fun searchByName(query: String) {

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun apiToDb() {
        binding.progressBar.isVisible = true;
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperheroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheros();
            if (myResponse.isSuccessful()) {
                Log.i("Consulta", "Funciona :)");
                val response: SuperheroDataResponse? = myResponse.body();
                if (response != null) {
                    Log.i("Cuerpo de la consulta", response.toString());

                    // Almacenar datos en Room
                    for (superhero in response.superheroes) {
                        listDao.insertAll();
                        detailDao.insertAll();
                    }

                    // Actualizar el adaptador con datos de Room
                    runOnUiThread {
                        adapter.updateList(listDao);
                        adapter.notifyDataSetChanged();
                        binding.progressBar.isVisible = false;
                    }
                }
            } else {
                Log.i("Consulta", "No funciona :(");
            }
        }
    }

}