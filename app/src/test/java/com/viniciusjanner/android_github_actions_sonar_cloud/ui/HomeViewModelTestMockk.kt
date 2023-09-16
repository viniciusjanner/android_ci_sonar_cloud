package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.viniciusjanner.android_github_actions_sonar_cloud.datastore.DataStoreManager
import com.viniciusjanner.android_github_actions_sonar_cloud.util.ThemeMode
import com.viniciusjanner.android_github_actions_sonar_cloud.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTestMockk {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var dataStoreManager: DataStoreManager

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataStoreManager = (mockk(relaxed = true))
        viewModel = HomeViewModel(dataStoreManager, Dispatchers.Unconfined)
    }

    @Test
    fun `fetchTheme() should emit DARK theme mode`() =
        //
        // fetchTheme() deve emitir o modo de tema DARK
        //
        runTest {
            // Arrange
            val expectedThemeMode = ThemeMode.DARK
            coEvery { dataStoreManager.getTheme() } returns flowOf(true)

            // Act
            viewModel.fetchTheme()

            // Assert
            val observedThemeMode = viewModel.themeLiveData.getOrAwaitValue()
            assertEquals(expectedThemeMode, observedThemeMode)
        }

    @Test
    fun `fetchTheme() should emit LIGHT theme mode`() =
        //
        // fetchTheme() deve emitir o modo de tema LIGHT
        //
        runTest {
            // Arrange
            val expectedThemeMode = ThemeMode.LIGHT
            coEvery { dataStoreManager.getTheme() } returns flowOf(false)

            // Act
            viewModel.fetchTheme()

            // Assert
            val observedThemeMode = viewModel.themeLiveData.getOrAwaitValue()
            assertEquals(expectedThemeMode, observedThemeMode)
        }

    @Test
    fun `setTheme() should update themeLiveData`() =
        //
        // setTheme() deve atualizar themeLiveData
        //
        runTest {
            // Arrange
            val expectedThemeMode = ThemeMode.DARK

            // Act
            viewModel.setTheme(expectedThemeMode)

            // Assert
            val observedThemeMode = viewModel.themeLiveData.getOrAwaitValue()
            assertEquals(expectedThemeMode, observedThemeMode)
        }
}
