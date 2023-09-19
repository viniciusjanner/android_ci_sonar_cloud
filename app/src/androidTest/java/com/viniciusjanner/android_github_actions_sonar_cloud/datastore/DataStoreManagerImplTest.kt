package com.viniciusjanner.android_github_actions_sonar_cloud.datastore

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataStoreManagerImplTest {

    private lateinit var dataStoreManager: DataStoreManager

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        dataStoreManager = DataStoreManagerImpl(context)
    }

    @Test
    fun shouldChange_ThemeMode_to_false() =
        //
        // deve mudar ThemeMode para false
        //
        runBlocking {
            val expectedThemeMode = false
            dataStoreManager.setTheme(expectedThemeMode)

            val observedThemeMode = dataStoreManager.getTheme().first()
            assertEquals(expectedThemeMode, observedThemeMode)
        }

    @Test
    fun shouldChange_ThemeMode_to_true() =
        //
        // deve mudar ThemeMode para true
        //
        runBlocking {
            val expectedThemeMode = true
            dataStoreManager.setTheme(expectedThemeMode)

            val observedThemeMode = dataStoreManager.getTheme().first()
            assertEquals(expectedThemeMode, observedThemeMode)
        }
}
