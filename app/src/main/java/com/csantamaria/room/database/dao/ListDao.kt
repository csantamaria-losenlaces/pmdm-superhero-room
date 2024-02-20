package com.csantamaria.room.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csantamaria.room.database.entities.ListEntity

@Dao
interface ListDao {

    @Query("DELETE FROM superheros_list")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll()

    @Query("SELECT * FROM superheros_list WHERE name LIKE :query")
    suspend fun getSuperheros(query: String): List<ListEntity>

    @Query("SELECT * FROM superheros_list WHERE id LIKE :id")
    suspend fun getSuperhero(id: Int): ListEntity

}