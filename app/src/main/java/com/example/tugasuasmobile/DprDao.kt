package com.example.tugasuasmobile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DprDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    fun insert(dpr: Dpr)

    @Query("SELECT * FROM favorite_table")
    fun getAll(): LiveData<List<Dpr>>
    @Query("SELECT * FROM favorite_table WHERE _id= :pkey limit 1 ")
    fun getDpr(pkey:String): Dpr?

    @Delete
    fun delete(dpr: Dpr)
}