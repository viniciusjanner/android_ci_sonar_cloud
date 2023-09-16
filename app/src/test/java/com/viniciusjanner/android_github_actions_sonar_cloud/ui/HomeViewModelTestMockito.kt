package com.viniciusjanner.android_github_actions_sonar_cloud.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.viniciusjanner.android_github_actions_sonar_cloud.datastore.DataStoreManager
import com.viniciusjanner.android_github_actions_sonar_cloud.util.LiveDataTestUtil
import com.viniciusjanner.android_github_actions_sonar_cloud.util.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTestMockito {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataStoreManager: DataStoreManager

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
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
            whenever(dataStoreManager.getTheme()).thenReturn(flowOf(true))

            // Act
            viewModel.fetchTheme()

            // Assert
            val observedThemeMode = LiveDataTestUtil.getValue(viewModel.themeLiveData)
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
            whenever(dataStoreManager.getTheme()).thenReturn(flowOf(false))

            // Act
            viewModel.fetchTheme()

            // Assert
            val observedThemeMode = LiveDataTestUtil.getValue(viewModel.themeLiveData)
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
            val observedThemeMode = LiveDataTestUtil.getValue(viewModel.themeLiveData)
            assertEquals(expectedThemeMode, observedThemeMode)
        }
}
