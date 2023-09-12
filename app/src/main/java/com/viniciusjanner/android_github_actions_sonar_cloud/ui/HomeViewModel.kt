package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.viniciusjanner.android_github_actions_sonar_cloud.prefs.DataStoreManager
import com.viniciusjanner.android_github_actions_sonar_cloud.prefs.ThemeMode
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    private val dataStoreManager: DataStoreManager,
) : AndroidViewModel(application) {

    val themeLiveData: LiveData<ThemeMode> = getTheme()

    private fun getTheme(): LiveData<ThemeMode> = dataStoreManager.getTheme()
        .map { isDarkMode ->
            if (isDarkMode) ThemeMode.DARK else ThemeMode.LIGHT
        }
        .asLiveData()

    fun setTheme(themeMode: ThemeMode) {
        val isDarkMode = (themeMode == ThemeMode.DARK)
        viewModelScope.launch {
            dataStoreManager.setTheme(isDarkMode)
        }
    }
}
