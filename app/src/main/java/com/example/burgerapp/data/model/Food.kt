package com.example.burgerapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val uuid: String = UUID.randomUUID().toString(),
    val name: String,
    val subtitle: String,
    val description: String,
    val rating: Double,
    @ColumnInfo(name = "image_res")
    val imageRes: Int,
    val price: Double,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Serializable