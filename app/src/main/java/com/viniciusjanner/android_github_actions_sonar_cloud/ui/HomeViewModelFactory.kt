package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viniciusjanner.android_github_actions_sonar_cloud.datastore.DataStoreManager
import kotlin.coroutines.CoroutineContext

class HomeViewModelFactory(
    private val dataStoreManager: DataStoreManager,
    private val coroutineContext: CoroutineContext,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataStoreManager, coroutineContext) as T
        }
        throw IllegalArgumentException("${HomeViewModelFactory::class.java.simpleName} : Unknown ViewModel class")
    }
}
