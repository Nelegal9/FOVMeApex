package com.alekhin.fovmeapex.onboarding

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("on_boarding_pref")

class OnBoardingDataStore(context: Context) {
    private object OnBoardingPreferences {
        val key = booleanPreferencesKey("on_boarding_completed")
    }

    private val onBoardingDataStore = context.dataStore

    fun readOnBoardingState(): Flow<Boolean> {
        return onBoardingDataStore.data.map {
            it[OnBoardingPreferences.key] ?: false
        }
    }

    suspend fun saveOnBoardingState() {
        onBoardingDataStore.edit {
            it[OnBoardingPreferences.key] = true
        }
    }
}