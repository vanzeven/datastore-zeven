package com.example.mydatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CounterDataStoreManager(private val context: Context) {

    suspend fun incrementCounter() {
        context.counterDataStore.edit { preferences ->
            val currentCounterValue = preferences[COUNTER_KEY]?:0
            preferences[COUNTER_KEY] = currentCounterValue+1
        }
    }

    suspend fun decrementCounter() {
        context.counterDataStore.edit { preferences ->
            val currentCounterValue = preferences[COUNTER_KEY]?:0
            preferences[COUNTER_KEY] = currentCounterValue-1
        }
    }

    suspend fun setCounter(counterValue: Int) {
        context.counterDataStore.edit { preferences ->
            preferences[COUNTER_KEY] = counterValue
        }
    }

    // A getter for the counter value flow
    fun getCounter(): Flow<Int> {
        return context.counterDataStore.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }
    }

    companion object {
        private const val DATASTORE_NAME = "counter_preferences"

        private val COUNTER_KEY = intPreferencesKey("counter_key")

        private val Context.counterDataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }
}