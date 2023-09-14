package com.viniciusjanner.android_github_actions_sonar_cloud.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    suspend fun getTheme(): Flow<Boolean>
    suspend fun setTheme(isDarkMode: Boolean)
}
