package com.csantamaria.room.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csantamaria.room.database.entities.DetailEntity

@Dao
interface DetailDao {

    @Query("DELETE FROM superhero_details")
    suspend fun deleteAll()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'superhero_details'")
    suspend fun resetId()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<DetailEntity>)

    @Query("SELECT * FROM superhero_details WHERE id LIKE :id")
    suspend fun getSuperheroDetails(id: String): DetailEntity

}