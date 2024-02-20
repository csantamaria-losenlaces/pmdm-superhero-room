package com.csantamaria.room.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csantamaria.room.database.entities.DetailEntity
import com.csantamaria.room.database.entities.ListEntity

@Dao
interface DetailDao {

    @Query("DELETE FROM superhero_details")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll()

    @Query("SELECT * FROM superhero_details WHERE id LIKE :id")
    suspend fun getSuperheroDetails(id: Int): DetailEntity

}