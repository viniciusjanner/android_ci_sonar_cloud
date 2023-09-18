package com.viniciusjanner.android_github_actions_sonar_cloud.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class DrawableMatcher(
    @DrawableRes private val id: Int,
    @ColorRes private val tint: Int? = null,
    private val tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN,
) : TypeSafeMatcher<View>() {

    companion object {
        const val EMPTY = -1
        const val ANY = -2
    }

    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
        tint?.let {
            description.appendText(", tint color id: $tint, mode: $tintMode")
        }
    }

    override fun matchesSafely(view: View): Boolean {
        //
        // ImageView
        //
        if (view is ImageView) {
            if (id == EMPTY) {
                return view.drawable == null
            }

            if (id == ANY) {
                return view.drawable != null
            }

            val expectedBitmap = getExpectedBitmap(view)

            return view.drawable.toBitmap().sameAs(expectedBitmap)
        }

        //
        // TextView
        //
        if (view is TextView) {
            if (id == EMPTY) {
                return false
            }

            if (id == ANY) {
                return false
            }

            val expectedBitmap = getExpectedBitmap(view)

            for (d in view.compoundDrawables) {
                if (d != null) {
                    if (d.toBitmap().sameAs(expectedBitmap)) {
                        return true
                    }
                }
            }

            return false
        }

        return false
    }

    private fun getExpectedBitmap(view: View): Bitmap {
        val context = view.context
        val tintColor = tint?.toColor(context)
        val expectedBitmap = context.getDrawable(id)!!.tinted(tintColor, tintMode).toBitmap()
        return expectedBitmap
    }

    private fun Drawable.tinted(
        @ColorInt tintColor: Int? = null,
        tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN,
    ): Drawable {
        return this.apply {
            setTintList(tintColor?.toColorStateList())
            setTintMode(tintMode)
        }
    }

    private fun Int.toColor(context: Context) = ContextCompat.getColor(context, this)

    private fun Int.toColorStateList() = ColorStateList.valueOf(this)
}
