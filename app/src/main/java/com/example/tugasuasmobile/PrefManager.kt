package com.example.tugasuasmobile

import android.content.Context
import com.google.gson.Gson

class PrefManager private constructor(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_FILENAME = "AuthAppPrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"

        @Volatile
        private var instance: PrefManager? = null

        fun getInstance(context: Context): PrefManager {
            return instance ?: synchronized(this) {
                instance ?: PrefManager(context.applicationContext).also { instance = it }
            }
        }
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
    fun saveData(dprs : List<Dpr>){
        val editor = sharedPreferences.edit()
        editor.putString("Dpr", Gson().toJson(dprs))
        editor.apply()
    }

    fun getData(): List<Dpr> {

        val data = sharedPreferences.getString("Dpr", "").orEmpty()
        return if (data.isNullOrEmpty()) {
            emptyList() // Return an empty list if data is null or empty
        } else {
            Gson().fromJson(data, Array<Dpr>::class.java).toList()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}