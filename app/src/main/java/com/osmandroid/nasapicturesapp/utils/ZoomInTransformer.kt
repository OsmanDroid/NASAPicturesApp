package com.osmandroid.nasapicturesapp.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2


class ZoomInTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val scale = if (position < 0.0f) position + 1.0f else Math.abs(1.0f - position)
        page.scaleX = scale
        page.scaleY = scale
        page.pivotX = page.width.toFloat() * 0.5f
        page.pivotY = page.height.toFloat() * 0.5f
        page.alpha = if (position >= -1.0f && position <= 1.0f) 1.0f - (scale - 1.0f) else 0.0f
    }
}