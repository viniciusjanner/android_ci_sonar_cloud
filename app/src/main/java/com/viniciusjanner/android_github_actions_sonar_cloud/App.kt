package com.viniciusjanner.android_github_actions_sonar_cloud

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.viniciusjanner.android_github_actions_sonar_cloud.prefs.DataStoreManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class App : MultiDexApplication() {

    companion object {
        lateinit var dataStoreManager: DataStoreManager
    }

    override fun onCreate() {
        super.onCreate()

        initThemeApp()
    }

    //
    // Se houver theme armazenado, o app já iniciará com o theme armazenado.
    //
    // Se não houver, o app iniciará com o theme default.
    //
    private fun initThemeApp() {
        dataStoreManager = DataStoreManager(this@App)

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("App", "coroutineExceptionHandler : throwable = ${throwable.message}")
            throwable.printStackTrace()
        }

        val coroutineContext: CoroutineContext = Dispatchers.Default + coroutineExceptionHandler

        CoroutineScope(coroutineContext).launch {
            dataStoreManager.getTheme().collect {
                AppCompatDelegate.setDefaultNightMode(
                    if (it) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    },
                )
            }
        }
    }
}
