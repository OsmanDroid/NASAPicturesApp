package com.osmandroid.nasapicturesapp.utils

import com.osmandroid.nasapicturesapp.utils.Extensions.getAbbreviatedDate
import com.osmandroid.nasapicturesapp.utils.Extensions.getLocalDate
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.time.LocalDate

class ExtensionsTest {

    @Test
    fun getLocalDate() {
        val testDateString = "2020-03-18"
        val expectedDate = LocalDate.parse(testDateString)
        assertTrue(expectedDate.equals(testDateString.getLocalDate()))
    }

    @Test
    fun getAbbreviatedDate() {
        val testDateString = "2020-03-18"
        val expectedResult = "March 18, 2020"
        assertTrue(expectedResult == testDateString.getAbbreviatedDate())
    }
}