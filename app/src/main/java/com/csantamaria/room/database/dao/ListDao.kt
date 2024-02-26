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

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'superheros_list'")
    suspend fun resetId()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ListEntity>)

    @Query("SELECT * FROM superheros_list")
    suspend fun getAll(): List<ListEntity>

    @Query("SELECT * FROM superheros_list WHERE id = :id")
    suspend fun getSuperhero(id: String): ListEntity

    @Query("SELECT * FROM superheros_list WHERE name LIKE '%' || :query || '%'")
    suspend fun searchByName(query: String): List<ListEntity>

}