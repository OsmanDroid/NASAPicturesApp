package com.osmandroid.nasapicturesapp.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = "src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner::class)
class NasaRepoTest {

    private lateinit var repo: NasaRepo

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        repo = NasaRepo(context)
    }


    @Test
    fun `get pictures list must not be null or empty`() = runBlocking {
        val result = repo.getPictures()
        result.collect {
            assertTrue(it.isNotEmpty())
        }
    }

}