package com.osmandroid.nasapicturesapp.utils

import android.content.Context
import android.view.View
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Extensions {

    fun Context.getCircularProgressDrawable(): CircularProgressDrawable {
        return CircularProgressDrawable(this).apply {
            strokeWidth = 6f
            centerRadius = 32f
            start()
        }
    }

    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun String.getLocalDate(): LocalDate? {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(this, dateTimeFormatter)
    }

    fun String.getAbbreviatedDate(): String {
        return try {
            val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
            dateTimeFormatter.format(this.getLocalDate())
        } catch (e: Exception) {
            ""
        }
    }
}