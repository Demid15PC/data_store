package com.example.datastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserEmail(private val context: Context) {

       private val prefs = context.getSharedPreferences("USER", Context.MODE_PRIVATE)

        fun saveUser(user: User) {
              prefs.edit().putString("user", Gson().toJson(user)).apply()
       }

       fun getUser(): User {
              val user = Gson().fromJson( prefs.getString("user", ""), User::class.java)
              return user
       }

}

data class User(
       val login: String,
       val password: String,
)