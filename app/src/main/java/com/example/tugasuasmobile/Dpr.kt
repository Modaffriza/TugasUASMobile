package com.example.tugasuasmobile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Dpr(
    @PrimaryKey
    val id: Int,
    val name: String,
    val photo: String
)