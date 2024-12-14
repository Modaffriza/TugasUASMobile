package com.example.tugasuasmobile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Dpr::class], version = 1, exportSchema = false)
abstract class DprDatabase : RoomDatabase() {
    abstract fun dprDao(): DprDao

    companion object {
        @Volatile
        private var INSTANCE: DprDatabase? = null

        fun getInstance(context: Context): DprDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DprDatabase::class.java,
                    "dpr_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}