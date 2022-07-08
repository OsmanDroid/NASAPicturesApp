package com.osmandroid.nasapicturesapp.utils

import android.content.Context
import android.view.View
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

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
}