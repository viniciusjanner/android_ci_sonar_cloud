package com.viniciusjanner.android_github_actions_sonar_cloud.util

import android.graphics.PorterDuff
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import org.hamcrest.Matcher

class EspressoMatchers {
    companion object {
        fun withDrawable(
            @DrawableRes id: Int,
            @ColorRes tint: Int? = null,
            tintMode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN,
        ): Matcher<View> {
            return DrawableMatcher(id, tint, tintMode)
        }

        fun noDrawable(): Matcher<View> {
            return DrawableMatcher(DrawableMatcher.EMPTY)
        }

        fun hasDrawable(): Matcher<View> {
            return DrawableMatcher(DrawableMatcher.ANY)
        }
    }
}
