package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viniciusjanner.android_github_actions_sonar_cloud.prefs.DataStoreManager

class HomeViewModelFactory(
    private val application: Application,
    private val dataStoreManager: DataStoreManager,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application, dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
