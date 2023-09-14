package com.viniciusjanner.android_github_actions_sonar_cloud.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManagerImpl(context: Context) : DataStoreManager {

    private var dataStore = context.dataStore

    companion object {

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "THEME_KEY")

        val darkModeKey: Preferences.Key<Boolean> = booleanPreferencesKey("DARK_MODE_KEY")
    }

    override suspend fun setTheme(isDarkMode: Boolean) {
        dataStore.edit { mutablePrefs ->
            mutablePrefs[darkModeKey] = isDarkMode
        }
    }

    override suspend fun getTheme(): Flow<Boolean> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { prefs ->
                val uiMode: Boolean = prefs[darkModeKey] ?: false
                uiMode
            }
    }
}
