package com.csantamaria.room.database

import androidx.room.Database
import com.csantamaria.room.database.entities.DetailEntity
import com.csantamaria.room.database.entities.ListEntity

@Database(entities = [ListEntity::class, DetailEntity::class], version = 1)
abstract class SuperheroDatabase {

}