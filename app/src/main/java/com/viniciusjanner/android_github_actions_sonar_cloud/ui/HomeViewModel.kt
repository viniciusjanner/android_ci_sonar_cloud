package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viniciusjanner.android_github_actions_sonar_cloud.datastore.DataStoreManager
import com.viniciusjanner.android_github_actions_sonar_cloud.util.ThemeMode
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val dataStoreManager: DataStoreManager,
    private val coroutineContext: CoroutineContext,
) : ViewModel() {

    private val _themeLiveData = MutableLiveData<ThemeMode>()
    val themeLiveData: LiveData<ThemeMode> = _themeLiveData

    fun fetchTheme() {
        viewModelScope.launch(coroutineContext) {
            dataStoreManager.getTheme().collect { isDarkMode ->
                val themeMode: ThemeMode = if (isDarkMode) ThemeMode.DARK else ThemeMode.LIGHT
                _themeLiveData.postValue(themeMode)
            }
        }
    }

    fun setTheme(themeMode: ThemeMode) {
        val isDarkMode = (themeMode == ThemeMode.DARK)
        viewModelScope.launch(coroutineContext) {
            dataStoreManager.setTheme(isDarkMode)
            _themeLiveData.postValue(themeMode)
        }
    }
}
