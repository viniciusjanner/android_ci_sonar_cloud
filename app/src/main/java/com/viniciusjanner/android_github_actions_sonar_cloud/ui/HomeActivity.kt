package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.viniciusjanner.android_github_actions_sonar_cloud.App
import com.viniciusjanner.android_github_actions_sonar_cloud.R
import com.viniciusjanner.android_github_actions_sonar_cloud.databinding.ActivityHomeBinding
import com.viniciusjanner.android_github_actions_sonar_cloud.prefs.ThemeMode
import com.viniciusjanner.android_github_actions_sonar_cloud.util.AppConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash Screen
        runBlocking {
            when (Build.VERSION.SDK_INT) {
                in AppConstants.sdkMin..AppConstants.sdkMax -> {
                    delay(AppConstants.delay)
                    setTheme(R.style.Theme_Home)
                }
                else -> {
                    installSplashScreen()
                    delay(AppConstants.delay)
                }
            }
        }

        // Home Screen
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initInstances()
        initObservers()
        initListeners()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    @Suppress("MaxLineLength")
    private fun initInstances() {
        val dataStoreManager = App.dataStoreManager
        viewModel = ViewModelProvider(this, HomeViewModelFactory(this.application, dataStoreManager))[HomeViewModel::class.java]
    }

    private fun initObservers() {
        viewModel.themeLiveData.observe(this@HomeActivity) { themeMode ->
            val isCheck = (themeMode == ThemeMode.DARK)

            binding.switchTheme.apply {
                isChecked = isCheck
                text = getString(if (isCheck) R.string.home_mode_dark else R.string.home_mode_light)
            }

            val nightMode = if (isCheck) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(nightMode)
            delegate.applyDayNight()
        }
    }

    private fun initListeners() {
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            val themeMode = if (isChecked) ThemeMode.DARK else ThemeMode.LIGHT
            viewModel.setTheme(themeMode)
        }
    }
}
