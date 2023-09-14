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
import com.viniciusjanner.android_github_actions_sonar_cloud.util.AppConstants
import com.viniciusjanner.android_github_actions_sonar_cloud.util.ThemeMode
import kotlinx.coroutines.Dispatchers
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
        viewModel = ViewModelProvider(this, HomeViewModelFactory(App.dataStoreManager, Dispatchers.Main))[HomeViewModel::class.java]
        viewModel.fetchTheme()
    }

    private fun initObservers() {
        viewModel.themeLiveData.observe(this@HomeActivity) { themeMode ->
            val isCheck: Boolean = (themeMode == ThemeMode.DARK)

            binding.switchTheme.apply {
                isChecked = isCheck
                text = getString(if (isCheck) R.string.home_mode_dark else R.string.home_mode_light)
            }

            val nightMode: Int = if (isCheck) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(nightMode)
            delegate.applyDayNight()
        }
    }

    private fun initListeners() {
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            val themeMode: ThemeMode = if (isChecked) ThemeMode.DARK else ThemeMode.LIGHT
            viewModel.setTheme(themeMode)
        }
    }
}
