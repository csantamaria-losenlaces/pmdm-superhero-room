package com.csantamaria.room.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.csantamaria.room.SuperheroItemResponse

@Entity(tableName = "superheros_list")
data class ListEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String
)

fun SuperheroItemResponse.toDatabase() = ListEntity(
    name = name,
    image = superheroImage.url
)