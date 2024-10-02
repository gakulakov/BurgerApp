package com.example.burgerapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.burgerapp.data.dao.CartDao
import com.example.burgerapp.data.model.Food

@Database(entities = [Food::class], version = 2)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "cart-database",
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}