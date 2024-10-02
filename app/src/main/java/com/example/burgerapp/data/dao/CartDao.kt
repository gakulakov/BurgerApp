package com.example.burgerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.burgerapp.data.model.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query ("SELECT * FROM food")
    fun getCart(): Flow<List<Food>>

    @Insert
    suspend fun add(food: Food)

    @Insert
    suspend fun addAll(vararg foods: Food)

    @Delete
    suspend fun remove(food: Food)
}