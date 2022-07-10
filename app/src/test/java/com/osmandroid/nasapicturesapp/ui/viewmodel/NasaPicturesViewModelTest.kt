package com.osmandroid.nasapicturesapp.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.osmandroid.nasapicturesapp.data.repository.NasaRepo
import com.osmandroid.nasapicturesapp.getOrAwaitValue
import com.osmandroid.nasapicturesapp.utils.Extensions.getLocalDate
import com.osmandroid.nasapicturesapp.utils.Resource
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner::class)
class NasaPicturesViewModelTest {

    private lateinit var viewModel: NasaPicturesViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = NasaPicturesViewModel(NasaRepo(context))
    }

    @Test
    fun `get pictures list must return success response`() {
        val result = viewModel.picturesList.getOrAwaitValue()
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `get pictures list must not be null or empty`() {
        val result = viewModel.picturesList.getOrAwaitValue()
        if (result is Resource.Success)
            assertTrue(result.value.isNotEmpty())
    }

    @Test
    fun `get pictures list must return the latest first based on date`() {
        val result = viewModel.picturesList.getOrAwaitValue()
        if (result is Resource.Success) {
            val expectedResult = result.value.sortedByDescending {
                it.date.getLocalDate()
            }
            assertTrue(result.value == expectedResult)
        }
    }
}