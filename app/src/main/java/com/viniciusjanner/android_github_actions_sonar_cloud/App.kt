package com.viniciusjanner.android_github_actions_sonar_cloud

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.viniciusjanner.android_github_actions_sonar_cloud.datastore.DataStoreManagerImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class App : MultiDexApplication() {

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
        //
        // Tratamento de possíveis exceções.
        //
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val tagLog = App::class.java.simpleName
            val messageLog = "coroutineExceptionHandler : throwable = ${throwable.message}"
            Log.e(tagLog, messageLog)

            throwable.printStackTrace()
        }

        val coroutineContext: CoroutineContext = (Dispatchers.Default + coroutineExceptionHandler)

        CoroutineScope(coroutineContext).launch {
            DataStoreManagerImpl(this@App)
                .getTheme()
                .collect {
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
