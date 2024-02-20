package com.csantamaria.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.csantamaria.room.database.dao.DetailDao
import com.csantamaria.room.database.dao.ListDao
import com.csantamaria.room.database.entities.DetailEntity
import com.csantamaria.room.database.entities.ListEntity

@Database(entities = [ListEntity::class, DetailEntity::class], version = 1)
abstract class SuperheroDatabase : RoomDatabase() {

    abstract fun getListDao(): ListDao
    abstract fun getDetailDao(): DetailDao

}