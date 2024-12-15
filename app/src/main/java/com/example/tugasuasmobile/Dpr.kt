package com.example.tugasuasmobile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_table")
data class Dpr(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("_id")
    val _id: String,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("partai")
    val partai: String,

    @SerializedName("fotoUrl")
    val fotoUrl: String
)